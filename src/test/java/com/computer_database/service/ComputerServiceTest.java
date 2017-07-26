package com.computer_database.service;


import com.computer_database.dao.IComputerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;

@RunWith(MockitoJUnitRunner.class)
public class ComputerServiceTest {

    @Mock
    private IComputerDao computerDao;

    @Mock
    private DataSource dataSource;

    @InjectMocks
    private ComputerService service;

    @Test
    public void testQuery() throws Exception {
        /*
        Mockito.when(dataSource.getConnection()).thenReturn(Mockito.any(Connection.class));
        Mockito.when(computerDao.getCountSearch(Mockito.any(Connection.class), "computer.name")).thenReturn(10);

        int check = service.getCountSearch("computer.name");
        assertTrue(check == 10);*/
    }

}
