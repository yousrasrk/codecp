#include<stdio.h>
#include<stdlib.h>
#include<math.h>
 
int main()
{
	int i, n;
	char move[100000];
	scanf("%d",&n);
	fflush(stdin);
	scanf("%[^\n]",&move);
	int left = 0;
	for(i = 0; i < n; i++)
		if((int)move[i] == 76)
			left++;
	int x = 0 - left;
	int y = 0 + n - left;
	
	printf("%d", abs(x - y) + 1);
}