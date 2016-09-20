package com.MThibProj.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.MThibProj.entity.Event;
import com.MThibProj.exceptions.NoSuchEventFoundException;

public class EventDao {

	public void registerEvent(Event event, Session session) {

		Transaction tx=null;
		try{

			tx = session.beginTransaction();

			session.save(event);
		}catch(Exception ex){
			System.out.println("Error in persisting the data in database");

		}
		tx.commit();
	


	}

	public Event findEvent(String eventName, Session session) throws NoSuchEventFoundException{

		Transaction tx=null;
		Event event=null;

		tx = session.beginTransaction();

		Criteria cr = session.createCriteria(Event.class);
		cr.add(Restrictions.eq("eventTitle", eventName));
		event=(Event) cr.uniqueResult();
		if(event==null){
			throw new NoSuchEventFoundException("No such event found");
		}
		tx.commit();
		

		return event;
	}


}
