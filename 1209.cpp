#include <bits/stdc++.h>
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int n;
set<int> val;

int main(){
	freopen("input.txt", "r", stdin);
	int count = 1; int i = 1;
	val.insert(i);
	while(i >= 0){
		i += count;
		count++;
		val.insert(i);
	}
	scanf("%i", &n);
	for(int i = 0; i < n; i++){
		int x;
		scanf("%i", &x);
		if(val.count(x))printf("%d ", 1);
		else printf("%d ", 0);
	}
	return 0;
}