package com.rtc;

/**
 * There is a hypothetical web server which supports only one very simple API - count of requests
 * received in the last hour, minute and second. This server is very popular in the world and
 * received thousands of requests per second.
 * 
 * Aim it to find how to return accurately these 3 counts to every request? Requests are coming all
 * the time, so the window of one hour, one minute and one second is different per request. How to
 * manage a different window per request so that the counts are correct per request?
 * 
 * 
 * You can create an array of size 60x60 for each second in the hour and use it as circular buffer.
 * Each entry contains number of requests for a given second. When you move to next second, clear it
 * and start counting. When you are at then end of array, you start from 0 again, so effectively
 * clearing all counts prior to 1 hour. For Hour: return sum of all elements For Minute: return sum
 * of last 60 entries (from currentIndex) For Second: return count on the currentIndex So all three
 * have O(1) space and time complexity. Only drawback is, it ignores milliseconds, but you can apply
 * same notion to include milliseconds as well.
 * 
 * @author surya
 *
 */
public class HitCounter {

}
