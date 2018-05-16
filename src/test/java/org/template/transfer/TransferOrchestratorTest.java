package org.template.transfer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.template.transfer.domain.Transfer;
import org.template.transfer.TransferOrchestrator;
import org.template.user.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransferOrchestratorTest {

    @InjectMocks
    private TransferOrchestrator transferOrchestrator;

    @Mock
    private UserDao userDao;

    @Test
    public void testCreateTransferReturnsFalseIfUserDoesNotExist() {
        final int userId = 1;
        final int amount = 5;
        final String action = "ADD";
        final Transfer transfer = new Transfer(userId, action, amount);

        when(userDao.doesUserExist(userId)).thenReturn(false);

        boolean result = transferOrchestrator.createTransfer(transfer);

        assertFalse(result);
    }
}
