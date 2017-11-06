package rest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import rest.dao.UniversityDao;

/**
 * The persistent class for the "Activity" database table.
 * 
 */
@Entity
@Table(name = "Activity")
@NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a")
@XmlRootElement(name="preferences")
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "idActivity")
	private int idActivity;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "place")
	private String place;
	
	@Column(name = "type")
	private String type;
	
	@Temporal(TemporalType.DATE)
	@Column(name="startdate")
	private Date startdate;
	
	@ManyToOne
	@JoinColumn(name="idPerson",referencedColumnName="idPerson")
	private Person person;

	public Activity() {
	}
	
	public int getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@XmlTransient
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public static Activity getActivityById(int activityId) {
		EntityManager em = UniversityDao.instance.createEntityManager();
		Activity p = em.find(Activity.class, activityId);
		UniversityDao.instance.closeConnections(em);
		return p;
	}
	
	//@XmlElementWrapper(name = "preferences")
	public static List<Activity> getAll() {
		System.out.println("--> Initializing Entity manager...");
		EntityManager em = UniversityDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the activities...");
	    List<Activity> list = em.createNamedQuery("Activity.findAll", Activity.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
		UniversityDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Activity saveActivity(Activity p) {
		EntityManager em = UniversityDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
		UniversityDao.instance.closeConnections(em);
	    return p;
	}
	
	public static Activity updateActivity(Activity p) {
		EntityManager em = UniversityDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
		UniversityDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeActivity(Activity p) {
		EntityManager em = UniversityDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    UniversityDao.instance.closeConnections(em);
	}
}
