class Marathon {

  // Implement your method here
  private int Fastest_time(int[] Recorded_Times){
    int index_fastest = 0;
    for (int i = 0; i < Recorded_Times.length; i++){
      if(Recorded_Times[index_fastest] > Recorded_Times[i]){
        index_fastest = i;
      }
    }
    return index_fastest;
  }

  public static void main (String[] args) {
    String[] names = { "Elena", "Thomas", "Hamilton", "Suzie",
    "Phil", "Matt", "Alex", "Emma", "John", "James", "Jane",
    "Emily", "Daniel", "Neda", "Aaron", "Kate" };

    int[] times = { 341, 273, 278, 329, 445, 402, 388, 275, 243,
      334, 412, 393, 299, 343, 317, 265};

    //Call your method here and, then print the name and time of the fastest runner.
    Marathon runner  = new Marathon();
    System.out.println("The fastest runner is : " +
    names[runner.Fastest_time(times)] + " with a time of " +
    times[runner.Fastest_time(times)] + "s");
  }
}
