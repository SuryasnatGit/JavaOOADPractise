package com.ratelimiter;

import java.util.concurrent.TimeUnit;

public final class TokenBuckets {

	private TokenBuckets() {
	}

	public static TokenBucket newFixedIntervalRefill(long capacityTokens, long refillTokens, long period,
			TimeUnit unit) {
		RefillStrategy strategy = new FixedIntervalRefillStrategy(refillTokens, period, unit);
		return new TokenBucket(capacityTokens, strategy);
	}

}
