void DumpS(int n);

int factorial(int n) {
   int result;
   DumpS(64);
   if(n <= 1) {
      result = 1;
   } else {
      result =  n * factorial(n-1);
   }
   DumpS(64);
   return result;
}
