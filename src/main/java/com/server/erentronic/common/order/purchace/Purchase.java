package com.server.erentronic.common.order.purchace;

import com.server.erentronic.common.order.Order;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase extends Order {

}
