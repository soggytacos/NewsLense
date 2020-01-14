package org.launchcode.service;


import org.junit.Before;
import org.junit.Test;
import org.launchcode.models.User;
import org.launchcode.models.data.RoleDao;
import org.launchcode.models.data.UserDao;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    @Mock
    private UserDao mockUserDao;

    @Mock
    private RoleDao mockRoleDao;

    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;
    private User user;

    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserDao, mockRoleDao, mockBCryptPasswordEncoder);

        user.setUserId(1);
        user.setFirstName("tester");
        user.setLastName("pants");
        user.setEmail("test@test.com");

        Mockito.when(mockUserDao.save(any())).thenReturn(user);
        Mockito.when(mockUserDao.findByEmail(anyString())).thenReturn(user);
    }

    @Test
    public void testFindUserByEmail() {
        // setup
        final String email = "test@test.com";

        // run the test
        final User result = userServiceUnderTest.findUserByEmail(email);

        // verify the results
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser() {
        //setup
        final String email = "test@test.com";

        //run the test
        User result= userServiceUnderTest.saveUser(User.builder().build());

        //verify the results
        assertEquals(email, result.getEmail());
    }

}
