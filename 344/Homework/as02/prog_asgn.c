#include <stdio.h>
#include <stdlib.h>
#include <time.h>


void dec_stat_arr() 
{
  static int STATIC_ARR[10240] = { 0 };
}

dec_heap_arr() 
{
  int *heap_arr = (int *) calloc(10240, sizeof(int));
}

dec_stack_arr() 
{
  int stack_arr[10240] = {0};
}

int main() 
{
  int i;
  float start_t, end_t;



  start_t = (float) clock()/CLOCKS_PER_SEC; 
  for (i =0; i < 200000; i+=1) 
  {
    dec_stat_arr();
  }
  end_t = (float) clock()/CLOCKS_PER_SEC;
  printf("%s %f %s\n", "It took", end_t - start_t, "to statically allocate an array of 10240 ints 200 thousand times.");

  start_t = (float) clock()/CLOCKS_PER_SEC;
  for (i =0; i < 200000; i+=1) 
  {
    dec_heap_arr();
  }
  end_t = (float) clock()/CLOCKS_PER_SEC;
  printf("%s %f %s\n", "It took", end_t - start_t, "to allocate an array of 10240 ints 200 thousand times on the heap");

  start_t = (float) clock()/CLOCKS_PER_SEC;
  for (i =0; i < 200000; i+=1) 
  {
    dec_stack_arr();
  }
  end_t = (float) clock()/CLOCKS_PER_SEC;
  printf("%s %f %s\n", "It took", end_t - start_t, "to allocate an array of 10240 ints 200 thousand times on the stack");
 


  return 0;
}
