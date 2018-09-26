package com.madfooat.billinquiry;

import com.madfooat.billinquiry.domain.Bill;
import com.madfooat.billinquiry.exceptions.InvalidBillInquiryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class JSONParseBillInquiryResponse implements ParseBillInquiryResponse {
    @Override
    public List<Bill> parse(String billerResponse) throws InvalidBillInquiryResponse {
        // Write your implementation

        //create List to save bills on it
        List<Bill> billsList = new ArrayList<Bill>();

        // parse JSON with Jackson using ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            Bill[] bills = objectMapper.readValue(billerResponse, Bill[].class);
            for(int i = 0; i<bills.length; i++){
                billsList.add(validate(bills[i]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return billsList;
    }

    

    // create method to validate the response
    private Bill validate(Bill bill){

        //"Bill Due Date, Amount" should be exist
        if(bill.getDueDate() == null | bill.getDueAmount() == null){
            return null;
        }

        //Bill due date should not be future date
        LocalDate localDate = LocalDate.now();  // current date
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());  // parse localdate to Date
        if (bill.getDueDate().after(date)) {
            //if the bill's date is after current day that mean we have future date
            return null;
        }

        //Amount should be of valid format in Jordainian Dinar
        //validate the Fees
        if(bill.getFees() != null){
            if(bill.getFees().compareTo(bill.getDueAmount()) > 1){
                bill.setFees(null);
            }
        }
        Bill newbill = new Bill(bill.getDueDate(),bill.getDueAmount());
        return newbill;
    }
}
