package client.model.account;

import hash.IService;
import hash.Service;
import org.parse4j.*;
import org.parse4j.callback.FindCallback;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by SamsungEvo on 3/3/2015.
 */
public class ParseDb {

    private static ParseDb instance;
    private  volatile boolean flag;
    private ParseDb(){}

    public static ParseDb getParseDb(){
        if(instance == null){
            Parse.initialize("q9dXUicyi8I3e8wEfCdXKXtv3cQqMZBkKk5p6kCT", "tF1V7py2Yz9dEgzT2wSeIoiH4Mw34jQFOksGzgeK");
            instance = new ParseDb();
        }

        return instance;
    }

    public void addAccount(final Account account){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Account");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null ){
                    if(scoreList == null)
                        accountHelper(account);
                    else{
                        flag = false;
                        for(ParseObject object : scoreList){
                            if(object.getString("Username").equals(account.getUserName())){
                                flag = true;

                            }
                            if(flag == false)
                            accountHelper(account);

                        }
                    }
                }
            }
        });
    }

    private void accountHelper(Account account){
        ParseObject parseAccount = new ParseObject("Account");
        parseAccount.put("Username",account.getUserName());
        parseAccount.put("Password",account.getHashedPassword());
        parseAccount.put("Salt",account.getSalt());
        parseAccount.put("Email", account.getEmail());
        try {
            parseAccount.save();
            flag = true;
        } catch (ParseException exception) {
            exception.printStackTrace();
            flag = false;
        }
    }

    public boolean validate(final String providedPassword, String username)  {

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Account");
        query.whereEqualTo("Username", username);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {

                if (e == null){

                    if(scoreList!=null){
                        for(ParseObject object : scoreList){
                            IService hashing = new Service().getBasicHttpBindingIService();

                            String hashCurrent = hashing.hash(providedPassword,scoreList.get(0).getString("Salt"));
                            System.out.println("provided password: "+providedPassword);
                            System.out.println("salt: "+  scoreList.get(0).getString("Salt"));
                            System.out.println("what we hashed: "+ hashCurrent);
                            System.out.println("actual password: "+ scoreList.get(0).getString("Password"));

                            if(hashCurrent.equals(object.getString("Password"))){
                                System.out.print("matched");
                                flag = true;

                            }
                        }
                    }


                    else
                        flag = false;
                }

            }
        });
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted waiting for password validation");
            e.printStackTrace();
        }
        return flag;
    }

    public void cleanUp(){

        try {
            ParseExecutor.getExecutor().shutdown();
            ParseExecutor.getExecutor().awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }


    public boolean removeAccount(final Account account){
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Account");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {

                if (e == null){
                    if(scoreList!=null){
                        for(ParseObject object : scoreList){
                            if(object.getString("Username").equals(account.getUserName())){
                                try {
                                    object.delete();
                                    flag = true;
                                } catch (ParseException e1) {
                                    e1.printStackTrace();
                                    flag = false;
                                }
                            }
                        }
                    }


                }

            }
        });
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return flag;
    }





}
