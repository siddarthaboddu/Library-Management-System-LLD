package com.siddarthaboddu.service;

import com.siddarthaboddu.entity.BookAction;

public interface PricingService {

	Double getOverDueCharge(BookAction bookAction);

}
