#include <bits/stdc++.h>
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int n, k;

int main(){
	freopen("input.txt", "r", stdin);
    scanf("%d %d",&n,&k);
    printf("%d\n",n <= k? 2 : (2*n + k - 1) / k);
	return 0;
}