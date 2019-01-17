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
int h, m, s;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> h >> m >> s;
	int starth = 12;
	int startm = 0;
	int starts = 0;
	h%=12;
	h+=m/60;
	m%=60;
	m+=s/60;
	s%=60;
	h+=m/60;
	m%=60;
	h%=12;
	starth-=h;
	if(startm - m < 0){
		starth--;
		startm = 60;
		startm-=m;
	}
	if(starts - s < 0){
		startm--;
		starts = 60;
		starts-=s;
	}
	cout << ((starth < 10 || starth == 12)?"0":"") << ((starth == 12)?"0":to_string(starth)) << ":" << ((startm < 10)?"0":"") << startm << ":" << ((starts < 10)?"0":"") << starts;
	return 0;
}