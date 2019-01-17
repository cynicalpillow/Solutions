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
int B;

int main(){
	freopen("input.txt", "r", stdin);
	cin >> B;
	cout << "$";
	printf("%.2lf ", (B/2.00));
	cout << "$";
	printf("%.2lf", B-(B/2.00));
	return 0;
}