#include<bits/stdc++.h>
using namespace std;
long long n,a[200000],i,b,j,r,p;
int main()
{
for(cin>>n;i<n;i++)cin>>b,a[i]=abs(b);
sort(a,a+n);
for(i=0;i<n;i++)
{
j=p;
while(a[++j]-a[i]<=a[i]&&j<n);
r+=j-i-1;
p=j-1;
}
cout<<r;
}