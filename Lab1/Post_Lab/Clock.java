class Clock{
  private int day, hour, minute, second;
  public Clock(){
    this.second = 0;
    this.minute = 0;
    this.hour = 0;
    this.day = 0;
  }
  public Clock(int day, int hour, int minute, int second){
    this.second = second;
    this.minute = minute;
    this.hour = hour;
    this.day = day;
  }
  private int getDay(){
    return day;
  }
  private int getHour(){
    return hour;
  }
  private int getMinute(){
    return minute;
  }
  private int getSecond(){
    return second;
  }
  private void setDay(int new_day){
    day += new_day;
  }
  private void setHour(int new_hour){
    hour = new_hour;
  }
  private void setMinute(int new_minute){
    minute = new_minute;
  }
  private void setSecond(int new_second){
    second = new_second;
  }
  private void increment(int new_second){
    second += new_second;
    if(second >= 60){
      second = 0;
      minute++;
    }
    if(minute >= 60){
      minute = 0;
      hour++;
    }
    if(hour >= 24){
      hour = 0;
      day++;
    }
  }
  private int calculateTotalSeconds(){
    int Total_Seconds = 0;
    Total_Seconds = second + minute*60 + hour*3600 + day*86400;
    return Total_Seconds;
  }

  public static void main(String [] args){
    // Create elapsed time with the default values of zeros for day, hour, minute, and second.
    Clock t1 = new Clock(); // Default constructor

    // sets hour to 23
    t1.setHour(23);
    // sets day to 1
    t1.setDay(1);
    // sets minute to 59
    t1.setMinute(59);
    // sets day to 16
    t1.setSecond(16);

    // prints 1:23:59:16
    System.out.println(t1.getDay() + ":" + t1.getHour() + ":" + t1.getMinute() + ":" + t1.getSecond());

    // Increments time t1 by 44 seconds
    t1.increment(44);
    // Prints 2:0:0:0
    System.out.println(t1.getDay() + ":" + t1.getHour() + ":" + t1.getMinute() + ":" + t1.getSecond());

    // prints the total elapsed time in seconds : 172,800
    System.out.printf("The elapsed time in seconds is : %d \n", t1.calculateTotalSeconds());

    // REPEAT SIMILAR TESTS FOR t2
    // Elapsed time is 3 days, 1 hour, 4 mins and 5 seconds
    Clock t2 = new Clock (3,1,4,5);
    System.out.println(t2.getDay() + ":" + t2.getHour() + ":" + t2.getMinute() + ":" + t2.getSecond());
  }
}
