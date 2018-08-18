package model;

import java.util.List;
import java.util.Random;

import model.interfaces.IBankAccount;
import model.interfaces.IUser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Entity(name = "BankAccount")
@Table(name = "bankaccount")
public class BankAccount implements IBankAccount {
    private static Random rnd = new Random(System.currentTimeMillis());

    private static Random getRnd() {
        return rnd;
    }

    private static final long newAcconutNumber() {
        return BankAccount.rnd.nextInt(1000 * 1000);
    }

    public static long newAccount(User user) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        BankAccount ba = new BankAccount(user);
        em.persist(ba);
        em.getTransaction().commit();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BankAccount> criteria = builder.createQuery(BankAccount.class);
        Root<BankAccount> root = criteria.from(BankAccount.class);
        criteria.where(builder.equal(root.get("user"), user.getId()));
        CriteriaQuery<BankAccount> select = criteria.select(root);
        Query q = em.createQuery(select);
        List<BankAccount> list = q.getResultList();
        emf.close();
        em.close();
        return list.get(0).accountNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public static JSONObject getAcconuts() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BankAccount> criteria = builder.createQuery(BankAccount.class);
        Root<BankAccount> root = criteria.from(BankAccount.class);
        CriteriaQuery<BankAccount> select = criteria.select(root);
        Query q = em.createQuery(select);
        List<BankAccount> list = q.getResultList();
        JSONArray a = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject t = new JSONObject();
            t.put("userName", list.get(i).user);
            t.put("accountNumber", list.get(i).accountNumber);
            t.put("fund", list.get(i).fund);
            a.add(t);
        }
        JSONObject res = new JSONObject();
        res.put("content", a);
        return res;
    }

    public long getId() {
        return id;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    private User user;

    @Basic
    private long accountNumber;

    public long getFund() {
        return fund;
    }

    public void setFund(long fund) {
        this.fund = fund;
    }

    public void saveFund(long fund) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        BankAccount tt = em.find(BankAccount.class, this.getId());
        em.getTransaction().begin();
        tt.setFund(fund);
        em.persist(tt);
        em.getTransaction().commit();
        em.close();
        emf.close();
        this.fund = fund;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Basic
    private long fund;

    public BankAccount() {
        this.user = null;
        this.accountNumber = newAcconutNumber();
    }

    public BankAccount(User user) {
        super();
        this.user = user;
        this.accountNumber = newAcconutNumber();
    }

    public BankAccount(String name) {
        this(new User(name));
    }

    public static BankAccount getAccount(long sourceAccountNumber) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BankAccount> criteria = builder.createQuery(BankAccount.class);
        Root<BankAccount> root = criteria.from(BankAccount.class);
        criteria.where(builder.equal(root.get("accountNumber"), sourceAccountNumber));
        CriteriaQuery<BankAccount> select = criteria.select(root);
        Query q = em.createQuery(select);
        List<BankAccount> list = q.getResultList();
        emf.close();
        em.close();
        return list.get(0);
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public IUser getUser() {
        return user;
    }

    @Override
    public void deposit(long value)
    {
        this.saveFund(this.getFund() + value);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        BankTransaction ba = new BankTransaction(this, null, value);
        em.persist(ba);
        em.getTransaction().commit();
        emf.close();
        em.close();
    }

    @Override
    public void withrow(long value) {
        if (this.getFund() >= value) {
            this.saveFund(this.getFund() - value);
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            BankTransaction ba = new BankTransaction(this, null, value);
            em.persist(ba);
            em.getTransaction().commit();
            emf.close();
            em.close();
        } else {
            throw new RuntimeException("deposit it");
        }
    }

    @Override
    public void transfer(BankAccount desteny, long value) {
        if (this.getFund() >= value) {
            this.saveFund(this.getFund() - value);
            desteny.saveFund(this.getFund() + value);
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            BankTransaction ba = new BankTransaction(this, desteny, value);
            em.persist(ba);
            em.getTransaction().commit();
            emf.close();
            em.close();
        } else {
            throw new RuntimeException("transfer it");
        }


    }
}
