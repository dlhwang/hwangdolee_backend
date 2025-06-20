package com.dollee.bank.common.util;

import de.huxhorn.sulky.ulid.ULID;
import java.io.Serial;
import java.io.Serializable;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UlidGenerator implements IdentifierGenerator, Serializable {

  @Serial private static final long serialVersionUID = -7858266607422294454L;

  @Override
  public Serializable generate(
      SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
    return new ULID().nextULID();
  }
}
