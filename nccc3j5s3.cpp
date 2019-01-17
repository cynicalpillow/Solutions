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
double xi, yi;
set<pair<double, double>> visited;
vector<pair<double, double>> ss;

bool checker(double radius, double pointX, double pointY, int i, int j, int z){
	int m = ss.size();
	for(int k = 0; k < m; k++){
		if(i == k || j == k || k == z)continue;
		if((ss[k].first - pointX)*(ss[k].first - pointX)+(ss[k].second - pointY)*(ss[k].second - pointY) > (radius*radius)){
			return false;
		}
	}
	return true;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	if(N == 1){
		cout << 0;
		return 0;
	}
	for(int i = 0; i < N; i++){
		cin >> xi >> yi;
		int prevSize = visited.size();
		visited.insert(mp(xi, yi));
		if(visited.size() > prevSize)ss.pb(mp(xi, yi));
	}
	if(ss.size() < 2){
		cout << 0;
		return 0;
	}
	if(N == 2){
		double dist = sqrt((ss[0].first-ss[1].first)*(ss[0].first-ss[1].first) + (ss[0].second-ss[1].second)*(ss[0].second-ss[1].second));
		double radius = dist/2.;
		printf("%.10f", radius);
		return 0;
	}
	int m = ss.size();
	double ans = -1;
	for(int i = 0; i < m-1; i++){
		for(int j = i+1; j < m; j++){
			double dist = sqrt((ss[i].first-ss[j].first)*(ss[i].first-ss[j].first) + (ss[i].second-ss[j].second)*(ss[i].second-ss[j].second));
			double radius = dist/2.;
			double pointX = (ss[i].first+ss[j].first)/2.;
			double pointY = (ss[i].second+ss[j].second)/2.;
			bool found = true;
			for(int k = 0; k < m; k++){
				if(i == k || j == k)continue;
				if((ss[k].first - pointX)*(ss[k].first - pointX)+(ss[k].second - pointY)*(ss[k].second - pointY) > (radius*radius)){
					found = false;
					break;
				}
			}
			if(found)ans = (ans == -1?radius:min(ans, radius));
		}
	}
	for(int i = 0; i < m; i++){
		for(int j = 0; j < m; j++){
			if(i == j)continue;
			for(int k = 0; k < m; k++){
				if(k == j || k == i)continue;
				double m1 = -((ss[i].first-ss[j].first)/(ss[i].second-ss[j].second));
				double m2 = -((ss[i].first-ss[k].first)/(ss[i].second-ss[k].second));

				bool verticalLine = ((ss[i].first-ss[j].first == 0 || ss[i].first-ss[k].first == 0) ? true : false);
				bool horizontalLine = ((ss[i].second-ss[j].second == 0 || ss[i].second-ss[k].second == 0) ? true : false);

				double px1 = (ss[i].first+ss[j].first)/2.;
				double py1 = (ss[i].second+ss[j].second)/2.;

				double px2 = (ss[i].first+ss[k].first)/2.;
				double py2 = (ss[i].second+ss[k].second)/2.;

				double b1 = (py1)-(m1*px1);
				double b2 = (py2)-(m2*px2);

				if(horizontalLine && verticalLine){
					double centerX = ss[i].first;
					double centerY = ss[i].second;
					double radius = sqrt((centerX - ss[k].first)*(centerX - ss[k].first) + (centerY - ss[k].second)*(centerY - ss[k].second));
					if(checker(radius, centerX, centerY, i, j, k))ans = (ans == -1?radius:min(ans, radius));
				} else if(verticalLine){
					if(ss[i].first-ss[j].first == 0){
						double centerX = ss[i].first;
						double centerY = (m2*centerX)+b2;
						double radius = sqrt((centerX - ss[k].first)*(centerX - ss[k].first) + (centerY - ss[k].second)*(centerY - ss[k].second));
						if(checker(radius, centerX, centerY, i, j, k))ans = (ans == -1?radius:min(ans, radius));
					} else {
						double centerX = ss[i].first;
						double centerY = (m1*centerX)+b1;
						double radius = sqrt((centerX - ss[j].first)*(centerX - ss[j].first) + (centerY - ss[j].second)*(centerY - ss[j].second));
						if(checker(radius, centerX, centerY, i, j, k))ans = (ans == -1?radius:min(ans, radius));
					}
				} else if(horizontalLine){
					if(ss[i].second-ss[j].second == 0){
						double centerX = (ss[i].second-b2)/m2;
						double centerY = (m2*centerX)+b2;
						double radius = sqrt((centerX - ss[i].first)*(centerX - ss[i].first) + (centerY - ss[i].second)*(centerY - ss[i].second));
						if(checker(radius, centerX, centerY, i, j, k))ans = (ans == -1?radius:min(ans, radius));
					} else {
						double centerX = (ss[i].second-b1)/m1;
						double centerY = (m1*centerX)+b1;
						double radius = sqrt((centerX - ss[i].first)*(centerX - ss[i].first) + (centerY - ss[i].second)*(centerY - ss[i].second));
						if(checker(radius, centerX, centerY, i, j, k))ans = (ans == -1?radius:min(ans, radius));
					}
				} else if(m1 - m2 == 0){
					double r1 = sqrt((ss[i].first-ss[j].first)*(ss[i].first-ss[j].first) + (ss[i].second-ss[j].second)*(ss[i].second-ss[j].second))/2.;
					double r2 = sqrt((ss[i].first-ss[k].first)*(ss[i].first-ss[k].first) + (ss[i].second-ss[k].second)*(ss[i].second-ss[k].second))/2.;
					double r3 = sqrt((ss[k].first-ss[j].first)*(ss[k].first-ss[j].first) + (ss[k].second-ss[j].second)*(ss[k].second-ss[j].second))/2.;
					double centerX, centerY;
					if(r1 > r2 && r1 > r3){
						centerX = (ss[i].first+ss[j].first)/2.;
						centerY = (ss[i].second+ss[j].second)/2.;
					} else if(r2 > r3 && r2 > r1){
						centerX = (ss[i].first+ss[k].first)/2.;
						centerY = (ss[i].second+ss[k].second)/2.;
					} else {
						centerX = (ss[k].first+ss[j].first)/2.;
						centerY = (ss[k].second+ss[j].second)/2.;
					}
					if(checker(max(r1, max(r2, r3)), centerX, centerY, i, j, k))ans = (ans == -1?max(r1, max(r2, r3)):min(ans, max(r1, max(r2, r3))));
				} else {
					double centerX = (b2-b1)/(m1-m2);
					double centerY = (m1*centerX)+b1;
					double radius = sqrt((centerX - ss[i].first)*(centerX - ss[i].first) + (centerY - ss[i].second)*(centerY - ss[i].second));
					if(checker(radius, centerX, centerY, i, j, k))ans = (ans == -1?radius:min(ans, radius));
				}
			}
		}
	}
	if(ans == -1)cout << 0;
	else printf("%.10f", ans);
	return 0;
}