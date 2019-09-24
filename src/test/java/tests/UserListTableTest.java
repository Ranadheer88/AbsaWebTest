package tests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExtentReports.User;
import utils.data.DataUtils;
import utils.ExtentReports.ExtentTestManager;


public class UserListTableTest extends BaseTest {

    @Test(priority = 0, description = "Validate User List Table.")
    public void validateUserListTable(Method method) {
        ExtentTestManager.startTest(method.getName(), "Validate User List Table.");

        userListPage
            .openUserListPage()
                .verifyUserListPage();
                //.addUser();

    }


    @DataProvider
    public Iterator<Object> userDataProvider(){
        Collection<Object> users = new ArrayList<Object>();
        ArrayList<User> userList = new ArrayList<>();
        try {
            userList = DataUtils.getTableArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userList.forEach(item -> users.add(item));
        return users.iterator();
    }

    @Test(dataProvider = "userDataProvider",dependsOnMethods = { "validateUserListTable" })
    public void addUserToTable(User user){
        ExtentTestManager.startTest("Add User", "Add user");
        userListPage.addUser(user).verifyUserAdded(user);
    }



}