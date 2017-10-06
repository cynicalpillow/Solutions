#include <bits/stdc++.h>
using namespace std;

int n;
std::vector<int> v;
int a;

int main(){
	freopen("input.txt", "r", stdin);
	cin >> n;
	for(int i = 0; i < n; i++){
		cin >> a;
		v.push_back(a);
	}
	for(size_t i = 0; i < v.size(); i++){
		printf("%d\n", v[i]*2);
	}
	return 0;
}