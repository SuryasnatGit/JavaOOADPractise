package com.ratelimiter;

public interface RefillStrategy {
	long refill();

	long getIntervalInMillis();
}
