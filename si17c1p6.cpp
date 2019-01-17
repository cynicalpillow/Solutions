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
int C;
long long T;
long long incomes[100005];

bool find_value(long long mid){
	long long total = 0;
	for(int i = 0; i < C; i++){
		if(incomes[i] < mid)total += abs(mid-incomes[i]);
	}
	return (total <= T);
}

long long upper_bound(long long min_value, long long max_value){
	long long low = min_value, high = T+max_value, mid;
	while (low < high) {
		mid = low + (high-low+1)/2;
		if (!find_value(mid))
			high = mid-1;
		else
			low = mid;
	}
	return low;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> C >> T;
	long long min_value = 1e12+5;
	long long max_value = 0;
	for(int i = 0; i < C; i++){
		cin >> incomes[i];
		min_value = min(min_value, incomes[i]);
		max_value = max(max_value, incomes[i]);
	}
	sort(incomes, incomes+C);
	cout << max(min_value, upper_bound(min_value, max_value));
	return 0;
}