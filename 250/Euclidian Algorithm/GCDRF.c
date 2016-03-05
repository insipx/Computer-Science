void DumpS(int n);

int gcd(int p, int q) {
  int result;
  DumpS(64);
  if(p == q) result = p;
  else result = gcd(q, p % q);

  DumpS(64);
  return result;

} 
