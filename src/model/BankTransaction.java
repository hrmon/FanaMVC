package model;

import model.interfaces.IBankAccount;
import model.interfaces.ITransaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Entity(name = "BankTransaction")
@Table(name = "banktransaction")
public class BankTransaction implements ITransaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@ManyToOne
	private final BankAccount sourceAccount;
    @ManyToOne
    private final BankAccount destinationAccount;
	@Basic
	private final long value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public BankTransaction() {
		super();
		this.sourceAccount = null;
		this.value = 0;
		this.destinationAccount = null;
	}
	public static JSONObject getTransactions() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<BankTransaction> criteria = builder.createQuery(BankTransaction.class);
		Root<BankTransaction> root = criteria.from(BankTransaction.class);
		CriteriaQuery<BankTransaction> select = criteria.select(root);
		Query q = em.createQuery(select);
		List<BankTransaction> list = q.getResultList();
		JSONArray a = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			JSONObject t = new JSONObject();
			t.put("sourceAccount", list.get(i).sourceAccount);
			t.put("destinationAccount", list.get(i).destinationAccount);
			t.put("value", list.get(i).value);
			a.add(t);
		}
		JSONObject res = new JSONObject();
		res.put("content", a);
		return res;
	}
	public BankTransaction(BankAccount sourceAccount, BankAccount destinationAccount, long value) {
		super();
		this.sourceAccount = sourceAccount;
		this.value = value;
		this.destinationAccount = destinationAccount;
	}

	public BankTransaction(BankAccount sourceAccount, long value) {
		super();
		this.sourceAccount = sourceAccount;
		this.value = value;
		this.destinationAccount = null;
	}

	public IBankAccount getDestinationAccount() {
		return destinationAccount;
	}

	public IBankAccount getSourceAccount() {
		return sourceAccount;
	}

	public long getValue() {
		return value;
	}

}
