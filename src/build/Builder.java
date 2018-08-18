package build;

import javax.persistence.Persistence;

public class Builder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        try {

            Persistence.generateSchema("pu", null);
        }catch (Exception e){
            e.printStackTrace();
        }
		String s = "CREATE TABLE 'fanadb'.'users' ( 'id' INT NOT NULL , 'name' INT NOT NULL , PRIMARY KEY ('id')) ENGINE = InnoDB;";

		String s2 = "CREATE TABLE 'fanadb'.'BankAccount' ( 'id' INT NOT NULL , 'userid' INT NOT NULL , 'amount' INT NOT NULL DEFAULT '0' , PRIMARY KEY ('id')) ENGINE = InnoDB;";
		String s3 = "ALTER TABLE 'bankaccount' ADD CONSTRAINT 'userToAccount' FOREIGN KEY ('userid') REFERENCES 'users'('id') ON DELETE RESTRICT ON UPDATE RESTRICT;";
	}

}
