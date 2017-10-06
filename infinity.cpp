#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
string a;
string b;
int val[6] = {10, 11, 12, 13, 14, 15};

int main(){
	//freopen("input.txt", "r", stdin);
	cin >> a >> b;
	int asum = 0;
	int bsum = 0;
	for(int i = a.length()-1; i >= 0; i--){
		if(isdigit(a[i]))asum+=(a[i]-'0')*pow(16.0, ((a.length()-1)-i));
		else asum+=(val[toupper(a[i])-'A'])*pow(16.0, ((a.length()-1)-i));
	}
	for(int i = b.length()-1; i >= 0; i--){
		if(isdigit(b[i]))bsum+=(b[i]-'0')*pow(16.0, ((b.length()-1)-i));
		else bsum+=(val[toupper(b[i])-'A'])*pow(16.0, ((b.length()-1)-i));
	}
	((asum+bsum) > 1061109567) ? printf("%s", "Yes") : printf("%s", "No");
	return 0;
}