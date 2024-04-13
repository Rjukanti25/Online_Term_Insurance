package com.example.insurance.mapper;
 
import java.util.Optional;

import org.springframework.stereotype.Component;
 
import com.example.insurance.dto.TransactionDTO;
import com.example.insurance.entity.Payment;
import com.example.insurance.entity.Policy;
import com.example.insurance.entity.Transaction;
import com.example.insurance.entity.Users;
import com.example.insurance.repository.PaymentRepository;
import com.example.insurance.repository.PolicyRepository;
import com.example.insurance.repository.UsersRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TransactionMapper {
	UsersRepository userRepo;
	PolicyRepository policyRepo;
	PaymentRepository paymentRepo;
	 public Transaction transactionDTOToTransaction(TransactionDTO dto) {
		 Transaction transaction = new Transaction();
	        transaction.setTransactionId(dto.getTransactionId());
	        Optional<Users> user0 = userRepo.findById(dto.getUserId());
	        transaction.setUser(user0.get());
	        Optional<Policy> policy0=policyRepo.findById(dto.getPolicyId());
	        transaction.setPolicies(policy0.get());
	        Optional<Payment> payment0=paymentRepo.findById(dto.getPaymentId());
	        transaction.setPayments(payment0.get());
	        transaction.setTransactionDate(dto.getTransactionDate());
	        transaction.setStatus(dto.getStatus());
	        return transaction;
	 }
 
	    public TransactionDTO transactionToTransactionDTO(Transaction transaction) {
	    	   TransactionDTO dto = new TransactionDTO();
		        dto.setTransactionId(transaction.getTransactionId());
		        dto.setUserId(transaction.getUser().getUserId());
		        dto.setPolicyId(transaction.getPolicies().getPolicyId());
		        dto.setPaymentId(transaction.getPayments().getPaymentId());
		        dto.setTransactionDate(transaction.getTransactionDate());
		        dto.setStatus(transaction.getStatus());
		        return dto;
	    }
}