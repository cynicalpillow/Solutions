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
int n;
string s;
int freq[3];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> n;
	getline(cin, s);
	for(int i = 0; i < n; i++){
		getline(cin, s);
		if(s == "Emperor Penguin")freq[0]++;
		else if(s == "Little Penguin")freq[1]++;
		else freq[2]++;
	}
	if(freq[0] > freq[1] && freq[0] > freq[2])cout << "Emperor Penguin";
	else if(freq[1] > freq[0] && freq[1] > freq[2])cout << "Little Penguin";
	else cout << "Macaroni Penguin";
	return 0;
}