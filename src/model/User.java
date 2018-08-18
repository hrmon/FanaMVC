package model;

import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.interfaces.IUser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Entity(name = "User")
@Table(name = "user")
public class User implements IUser {

    private static long mod = 1000 * 1000 * 1000 + 7;

    private static long passHashFunction(String s) {
        long res = 0;
        for (int i = 0; i < s.length(); i++) {
            res *= 10;
            res += s.charAt(i);
            res %= mod;
        }
        return res;
    }

    public static int addUser(String userName, String pass) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        int employeeID = 0;
        User u = new User(userName, pass);
        em.persist(u);
        em.getTransaction().commit();
        emf.close();
        em.close();
        return employeeID;
    }

    public static User getUser(String userName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.where(builder.equal(root.get("name"), userName));
        CriteriaQuery<User> select = criteria.select(root);
        Query q = em.createQuery(select);
        List<User> list = q.getResultList();
        return list.get(0);
    }

    public static JSONObject getUsers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        CriteriaQuery<User> select = criteria.select(root);
        Query q = em.createQuery(select);
        List<User> list = q.getResultList();
        JSONArray a = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject t = new JSONObject();
            t.put("userName", list.get(i).name);
            a.add(t);
        }
        JSONObject res = new JSONObject();
        res.put("content", a);
        return res;
    }

    public void setPass(long pass) {
        this.pass = pass;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    String name;
    @Basic
    long pass;

    public User() {
        this.name = "";
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String pass) {
        this.name = name;
        this.pass = passHashFunction(pass);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = passHashFunction(pass);
    }

    public long getPass() {
        return pass;
    }
}
