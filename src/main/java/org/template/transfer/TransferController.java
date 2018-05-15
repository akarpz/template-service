package org.template.transfer;

import java.util.List;

import org.template.transfer.domain.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    @Autowired
    private TransferOrchestrator transferOrchestrator;

    @RequestMapping(path = "/api/v1/transfers", method = RequestMethod.POST)
    public boolean createTransfer(@RequestBody Transfer transfer) {
        return transferOrchestrator.createTransfer(transfer);
    }

    @RequestMapping(path = "/api/v1/transfers/list/{userId}", method = RequestMethod.GET)
    public List<Transfer> getTransfersForUserId(@PathVariable int userId) {
        return transferOrchestrator.getTransfersForUserId(userId);
    }

    @RequestMapping(path = "/api/v1/transfers/sum/{userId}", method = RequestMethod.GET)
    public Integer getTransferSumForUserId(@PathVariable int userId) {
        return transferOrchestrator.getTransferSumForUserId(userId);
    }

}