package client.model.account;

import hash.IService;
import hash.Service;


public class Account {

    private String userName;
    private String hashedPassword;
    private String email;
    private String salted;
    private IService hashService;

    public Account(String userName, String password,String email){



        hashService = new Service().getBasicHttpBindingIService();
        this.userName = userName;
        this.email = email;
        salted = generateSecureSalt();
        hashedPassword = hashService.hash(password,salted);



    }

    private String generateSecureSalt(){

        salt.IService service = new salt.Service().getBasicHttpBindingIService();
        return service.getRandomString0();
}

    public String getUserName(){return userName;}

    public String getHashedPassword(){return hashedPassword;}

    public String getEmail(){return email;}

    public String getSalt(){return salted;}




}
