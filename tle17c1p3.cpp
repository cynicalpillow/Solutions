#include <iostream>
#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, T, m1;
string x;
bool first;
int cnt;
int a[100005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> T;
	cin >> x;
	for(int i = 0; i < N; i++){
		if(x[i] == '|')a[i] = 1;
		if(a[i] == 1 && i == 0)first = true;
		if(a[i] == 1)cnt++;
	}
	for(int i = 0; i < T; i++){
		cin >> m1;
		m1--;
		if(m1 == 0){
			if(a[m1] == 0){
				first = true;
				cnt++;
			} else {
				first = false;
				cnt--;
			}
		} else {
			if(a[m1] == 0)cnt++;
			else cnt--;
		}
		a[m1] = !a[m1];
		cout << ((first)?1:(N+(2*cnt))) << "\n";
	}
	return 0;
}