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
int A, B;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> A >> B;
	int c = 0;
	for(int i = 1; i <= B; i+=2)c++;
	int b = 0;
	for(int i = 1; i < A; i+=2)b++;
	cout << c-b;
	return 0;
}