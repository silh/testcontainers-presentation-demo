package com.silh.example.testcontainersdemo.entities;


import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public class OrderMetadata implements Serializable {
  private String tag;
}
