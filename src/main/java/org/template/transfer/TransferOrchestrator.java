package org.template.transfer;

import java.util.List;

import org.template.transfer.domain.Transfer;
import org.template.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferOrchestrator {

    @Autowired
    private TransferDao transferDao;

    @Autowired
    private UserDao userDao;

    public boolean createTransfer(Transfer transfer) {

        final int userId = transfer.getUserId();
        final int amount = transfer.getAmount();

        if(!userDao.doesUserExist(userId)) {
            return false;
        }

        switch (transfer.getAction()) {
            case "ADD":
                return transferDao.createTransfer(userId, amount);
            case "REMOVE":
                return !(transferDao.getTransferSumForUserId(userId) < amount) && transferDao.createTransfer(userId, amount);
        }

        return false;
    }

    List<Transfer> getTransfersForUserId(int userId) {
        return transferDao.getTransfersForUserId(userId);
    }

    Integer getTransferSumForUserId(int userId) {
        return transferDao.getTransferSumForUserId(userId);
    }
}
