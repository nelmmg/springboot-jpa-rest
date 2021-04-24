package com.company.sample.support.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

  @JsonInclude(Include.NON_NULL)
  private UUID id;
  private String sender;
  private String receiver;
  private double amount;

}
