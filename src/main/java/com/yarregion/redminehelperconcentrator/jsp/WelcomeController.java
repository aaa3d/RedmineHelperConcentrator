/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yarregion.redminehelperconcentrator.jsp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class WelcomeController {
    
        @Autowired	private SessionFactory sessionFactory;


	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		
                
                Session session = sessionFactory.openSession();
                Criteria criteria = session.createCriteria(Worker.class);
                List<Worker> workers =  criteria.list();
                
                for(Worker worker: workers){
                   Calendar cal = Calendar.getInstance();
                   cal.add(Calendar.DATE, -7);
                   Criteria crit = session.createCriteria(Issue.class);
                   crit.add(Restrictions.ge("last_timer_tm", cal));
                   crit.add(Restrictions.eq("worker", worker));
                   crit.addOrder( Order.desc("last_timer_tm"));
                   worker.last_issues = crit.list();
                }
                
                //вывести страницу с текищими задачами
                //юзер последний коннект задачи в работе 
                
                
                session.close();
                
                model.put("workers", workers);
                
		return "welcome";
	}


        @RequestMapping("updateData")
        public @ResponseBody String updateData(String login, String fio, int issue_id, String issue_subject, double issue_timer_value){
            
            Session session = sessionFactory.openSession();
            Worker worker = (Worker) session.get(Worker.class, login);
            if (worker == null)
                worker = new Worker();
            
            
            Issue issue = (Issue) session.get(Issue.class, issue_id);
            if (issue == null)
                issue = new Issue();
            
            worker.login = login;
            worker.fio = fio;
            worker.last_ping_tm = Calendar.getInstance();
            
            issue.id = issue_id;
            issue.subject = issue_subject;
            issue.timer_value = issue_timer_value;
            issue.worker = worker;
            issue.last_timer_tm = Calendar.getInstance();
            
            //session.sa(worker);
            //session.persist(issue);
            session.save(worker);
            session.save(issue);
            
            session.flush();
            session.close();
            
            return "OK";
        }
        
        /*
        @RequestMapping(value="{name}", method = RequestMethod.GET)
	public @ResponseBody Shop getShopInJSON(@PathVariable String name) {

		Shop shop = new Shop();
		shop.setName(name);
		shop.setStaffName(new String[]{"mkyong1", "mkyong2"});

		return shop;

	}
        */


}
