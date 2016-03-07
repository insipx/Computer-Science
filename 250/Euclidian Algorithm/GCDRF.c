void DumpS(int n);

int gcd(int p, int q) {

  int result;
  DumpS(64);

  if(p == 0) result = q;
  else result = gcd(q % p, p);

  DumpS(64);

  return result;

} 
