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
int N;
double S;
int ti;
double a, b, c, d, e, f;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> S;
	a = b = c = d = e = f = 0;
	double combo = 0.;
	for(int i = 0; i < N; i++){
		cin >> ti;
		ti = abs(ti);
		if(ti <= 50)a++;
		else if(ti <= 100)b++;
		else if(ti <= 150)c++;
		else if(ti <= 200)d++;
		else e++;
		if(ti > 200)combo = 0.;
		else combo++;
		f = max(f, combo);
	}
	printf("%.1f", max(0., ((1.5*a+b+c+0.5*d-e+0.5*f) - S)));
	return 0;
}