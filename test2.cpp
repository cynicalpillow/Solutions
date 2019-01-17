#include <bits/stdc++.h>
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

//Utilities
int lower_bound (int arr[], int x) {
	int low = 0, high = (sizeof(arr)/arr[0]), mid = -1;
	while (low < high) {
		mid = (low + high) / 2;
		if (arr[mid] >= x)
			high = mid;
		else
			low = mid + 1;
	}
	return low;
}

int upper_bound (int arr[], int x) {
	int low = 0, high = (sizeof(arr)/arr[0]), mid = -1;
	while (low < high) {
		mid = (low + high) / 2;
		if (arr[mid] > x)
			high = mid;
		else
			low = mid + 1;
	}
	return low;
}

int gcd (int a, int b) {
	return b == 0 ? a : gcd (b, a % b);
}

int lcm (int a, int b) {
	return a * b / gcd (a, b);
}

int fast_pow_mod (int b, int x, int mod) {
	if (x == 0) return 1;
	if (x == 1) return b;
	if (x % 2 == 0) return fast_pow_mod (b * b % mod, x / 2, mod) % mod;
	return b * fast_pow_mod (b * b % mod, x / 2, mod) % mod;
}

int fast_pow (int b, int x) {
	if (x == 0) return 1;
	if (x == 1) return b;
	if (x % 2 == 0) return fast_pow (b * b, x / 2);
	return b * fast_pow (b * b, x / 2);
}

long choose (long n, long k) {
	k = min(k, n - k);
	long val = 1;
	for (int i = 0; i < k; ++i)
		val = val * (n - i) / (i + 1);
	return val;
}

long permute (int n, int k) {
	if (n < k) return 0;
	long val = 1;
	for (int i = 0; i < k; ++i)
		val = (val * (n - i));
	return val;
}

int INFINITE = 0x3f3f3f3f;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	
	return 0;
}