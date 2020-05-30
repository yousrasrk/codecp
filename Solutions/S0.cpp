#include <cstdio>
#include <iostream>
#include <memory.h>
using namespace std;
const int MAX_BIT = 32;
int au2[MAX_BIT]; 
int bu2[MAX_BIT]; 
long long dp[MAX_BIT][2][2]; 
long long dfs(int pos,bool alim,bool blim) {
    if(pos == -1) return 1;
    if(dp[pos][alim][blim] != -1) return dp[pos][alim][blim];
 
    int aupper = alim? au2[pos] : 1;
    int bupper = blim? bu2[pos] : 1;
 
    long long ans = 0;
    for(int i=0;i<=aupper;i++) {
        for(int j=0;j<=bupper;j++) {
            if((i&j) == 0)
            ans += dfs(pos-1,alim&&(i==aupper),blim&&(j==bupper));
        }
    }
    dp[pos][alim][blim] = ans;
    return ans;
}
long long cal(int au,int bu) {
    memset(dp,-1,sizeof(dp));
    for(int i=0;i<MAX_BIT;i++) au2[i]=bu2[i]=0;
    int len = 0;
    while (au || bu)
    {
        au2[len] = au & 1;
        bu2[len] = bu & 1;
        au >>= 1;
        bu >>= 1;
        len++; 
    }
    return dfs(MAX_BIT-1,true,true);
}
 
int main() {
    int T;
    cin>>T;
    while(T--) {
        int l,r;
        cin>>l>>r;
        if(l == 0) cout << cal(r,r) << endl;
        else cout << cal(r,r)-2*cal(l-1,r)+cal(l-1,l-1) << endl;
    }
    return 0;
}