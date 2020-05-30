#include<bits/stdc++.h>
typedef long long ll;
using namespace std;
signed main()
{
	int t;
	scanf("%d",&t);
	while(t--)
	{
		ll n,m,i,g,ans,x;
		scanf("%lld%lld",&n,&m);
		g=__gcd(m,n);
		ans=x=m/g;
		for(i=2;i*i<=x;i++)
		{
			if(x%i==0)
			{
				ans-=ans/i;
				while(x%i==0)x/=i;
			}	
		}
		if(x>1)ans-=ans/x;
		printf("%lld\n",ans);
	}
	return 0;
}