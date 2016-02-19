// IntegerOps.c
// P.M.J. - Spring 2016

int Max(int fa1, int fa2) {
  int result = fa1;
  if(fa2 > fa1) {
    result = fa2;
  }
  return result;
}

int Min(int fa1, int fa2) {
  int result = fa1;
  if(fa2 < fa1) {
    result = fa2;
  }
  return result;
}

int Max3(int fa1, int fa2, int fa3) {
  int result = Max(fa1,fa2);
  if(fa3 > result) {
    result = fa3;
  }
  return result;
}

int Min3(int fa1, int fa2, int fa3) {
  int result = Min(fa1,fa2);
  if(fa3 < result) {
    result = fa3;
  }
  return result;
 }

int MaxAIndex(int a[], int lower, int upper) {
  int result = lower;
  int index;
  for(index=(lower+1); index<upper; index++) {
    if(a[index] > a[result]) {
      result = index;
    }
  }
  return result;
}

int MaxA(int a[], int lower, int upper) {
  int result = 0;
  int index = MaxAIndex(a,lower,upper);
  if((index >= lower) && (index < upper)) {
    result = a[index];
  }
  return result;
}
   
int MinAIndex(int a[], int lower, int upper) {
  int result = lower;
  int index;
  for(index=(lower+1); index<upper; index++) {
    if(a[index] < a[result]) {
      result = index;
    }
  }
  return result;
}

int MinA(int a[], int lower, int upper) {
  int result = 0;
  int index = MinAIndex(a,lower,upper);
  if((index >= lower) && (index < upper)) {
    result = a[index];
  }
  return result;
}
