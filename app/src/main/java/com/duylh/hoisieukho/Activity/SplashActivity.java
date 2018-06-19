package com.duylh.hoisieukho.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.duylh.hoisieukho.Model.CauHoi;
import com.duylh.hoisieukho.Model.SQLite;
import com.duylh.thanhtrolltrotai.R;
import com.roger.catloadinglibrary.CatLoadingView;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    CatLoadingView mView;
    public static SQLite db;
    ArrayList<CauHoi> mangCau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mangCau = new ArrayList<>();

        db = new SQLite(SplashActivity.this, "db_cauhoi", null, 1);
        db.QueryData("CREATE TABLE IF NOT EXISTS Question_Table(Id INTEGER PRIMARY KEY AUTOINCREMENT, TTCauHoi INTEGER, CauHoi VARCHAR, DapanA VARCHAR, DapanB VARCHAR, DapanC VARCHAR, DapanD VARCHAR, DapAn VARCHAR)");
        db.QueryData("CREATE TABLE IF NOT EXISTS XepHang_Table(Id INTEGER PRIMARY KEY AUTOINCREMENT, Player VARCHAR, Diem INTEGER)");

        //db.QueryData("DELETE FROM Question_Table");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Cursor c = db.GetData("SELECT * FROM Question_Table");
                while(c.moveToNext()){
                    mangCau.add(new CauHoi(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7)));
                }
                if(mangCau.size()==0){
                    new XuLyAddQuestion().execute();
                }else {

                }

            }
        });
        mView = new CatLoadingView();
        mView.setCancelable(false);
        mView.show(getSupportFragmentManager(), "");


        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    mView.dismiss();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }


    private class XuLyAddQuestion extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... strings) {
            AddQuestion();
            return null;
        }
    }
    public void showDialog() {
        mView.show(getSupportFragmentManager(), "");
    }
    public static void AddQuestion(){
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Người đàn ông duy nhất trên thế giới có sữa là ai?', 'Ông Tài', 'Ông Thọ', 'Ông Phúc', 'Ông Lộc', 'Ông Thọ')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Đâu là tên một loài hoa', 'Hoa Khôi', 'Hoa Hậu', 'Hoa Mỹ', 'Hoa Mai', 'Hoa Mai')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Đâu không phải là tên một hãng điện thoại', 'Huawei', 'Z1000', 'Pantech', 'Iphone', 'Z1000')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Bệnh gì bác sĩ phải Bó Tay?', 'Gãy tay', 'Ung thư', 'Viêm gan', 'Chán đời', 'Gãy tay')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Quả gì bự nhất?', 'Quả bưởi', 'Quả táo', 'Quả nho', 'Quả đất', 'Quả đất')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'An Dương Vương đặt quốc hiệu nước ta là gì', 'Văn Lang', 'Đại Việt', 'Âu Lạc', 'Vạn Xuân', 'Văn Lang')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Cúm và sởi lây tryền qua đường nào?', 'Hô hấp', 'Máu', 'Tiêu hóa', 'Tình dục', 'Hô hấp')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Bánh Pía là đặc sản có nguồn gốc từ tỉnh nào?', 'Sóc Trăng', 'Cần Thơ', 'Dắk Lắk', 'Hưng Yên', 'Sóc Trăng')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Ngôn ngữ nào sau đây không phải là ngôn ngữ lập trình?', 'Pascal', 'C++', 'Python', 'CRT', 'CRT')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Làm thế nào để qua được câu này?', 'Bỏ cuộc', 'Trả lời đúng', 'Không thể qua', 'Trợ giúp', 'Trả lời đúng')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Loại nước giải khát nào chưa cả sắt và canxi?', 'Cafe', 'Sữa tươi', 'Trà chanh', 'Number 1', 'Cafe')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Cách 1 bước nhưng bước mai không tới. Là gì?', 'Bóng', 'Tương lai', 'Người yêu', 'Hư cấu', 'Bóng')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Con trai quý nhất cái gì?', 'Tiền', 'Đầu óc', 'Cái ấy', 'Ngọc', 'Ngọc')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Núi nào bị chặt ra từng khúc?', 'Thái Sơn', 'Bà Đen', 'Everest', 'Phú Sĩ', 'Thái Sơn')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Có bao nhiêu chữ C trong câu sau đây: “Cơm, canh, cháo gì tớ cũng thích ăn!”?', 1, 2, 3, 4, 1)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 1, 'Ngọc nào đẹp nhất Việt Nam?', 'Ngọc Trai', 'Ngọc Nữ', 'Ngọc Trinh', 'Thua :((', 'Ngọc Trinh')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'What is between the sky and earth?', 'Air', 'Water', 'Terra', 'And', 'And')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'Vì sao người ta chết?', 'Chán sống', 'Bị giết', 'Tim ngừng đập', 'Cái số', 'Tim ngừng đập')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'Quần rộng nhất là quần gì?', 'Quần cụt', 'Quần đảo', 'Quần vợt', 'Quần lửng', 'Quần đảo')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'Xã nào lớn nhất?', 'Xã hội', 'Xã giao', 'Xã Quảng Điền', 'Đếch biết', 'Xã hội')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'Từ gì mà 100% người Việt Nam đều phát âm sai?', 'Sanh', 'Sai', 'Sao', 'So', 'Sai')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'Sở thú bị cháy, con gì chạy ra đầu tiên?', 'Con Hổ', 'Sư Tử', 'Con Khỉ', 'Con Người', 'Con Người')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'Người đẹp Monalisa không có thứ gì?', 'Tiền', 'Thật', 'Lông', 'Chồng', 'Chồng')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'Cha của Ly có 5 người con tên thứ Nhất, thứ Hai, thứ Ba, thứ Năm, hỏi tên của người con thứ 5 là gì?', 'Thứ Sáu', 'Thứ Bảy', 'Ai biết? :O', 'Ly', 'Ly')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'Con gì đáng sợ nhất?', 'Rắn', 'Bọ cạp', 'Rết', 'Con người', 'Con người')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'Thứ gì có thể làm giả mà không phạm pháp?', 'Tiền', 'Xe đạp', 'Điện thoại', 'Răng giả', 'Răng giả')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'Người sử dụng không hề hay biết?', 'Tiền', 'Xe đạp', 'Điện thoại', 'Quan tài', 'Quan tài')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'Trong một cuộc thi, nếu bạn vượt qua người thứ hai. Bạn sẽ về thứ mấy?', 'Nhất', 'Nhì', 'Ba', 'Hỏi gì thế?', 'Nhì')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, '1 tấn bông gòn và 1 tấn sắt, đố bạn cái nào nặng hơn?', 'Bông gòn', 'Sắt', 'Hên xui', 'Bằng nhau', 'Bằng nhau')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'Bố mẹ có 6 người con trai, mỗi người con trai có 1 cô em gái. Hỏi gia đình đó có mấy người?', 8, 9, 10, 11, 9)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'Người Eskimo bắt đầu săn chim cánh cụt từ khi nào?', 1984, 1965, 1977, 'Chưa bao giờ', 'Chưa bao giờ')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 2, 'Loài chim nào có khả năng bay ngược?', 'Chim cu', 'Chim cánh cụt', 'Chim ruồi', 'Chim gõ kiến', 'Chim ruồi')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 3, 'Where does today come before yesterday?', 'Vietnam', 'Lao', 'Dictionary', 'Japan', 'Dictionary')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 3, '2 con vịt đi trước 2 con vịt, 2 con vịt đi sau 2 con vịt, 2 con vịt đi giữa 2 con vịt. Hỏi có mấy con vịt?', 4, 5, 6, 8, 4)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 3, 'Tay phải mày cầm 3 trái lê, 4 trái táo, tay trái mày cầm 4 trái bưởi. Hỏi mày có gì?', 'Có trái cây', 'Có tiền', 'Có 1 bàn tay to vl', 'Có sức khỏe :))', 'Có 1 bàn tay to vl')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 3, 'Con gì đập thì sống, không đập thì chết?', 'Con Chim', 'Con Gà Mái', 'Con Tim', 'Con Gà Trống', 'Con Tim')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 3, 'Loài vật nào sau đây có thể thở bằng mông?', 'Kangaroo', 'Rùa', 'Cá mập', 'Chồn hương', 'Rùa')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 3, '1 người đi vào rạp chiếu phim gặp 1 con hổ chết, hỏi tại sao người đó quay về?', 'Hết chỗ', 'Sợ hãi', 'Mất vé', 'Quên ghệ ở nhà', 'Hết chỗ')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 3, '1 giọt nước cộng 1 giọt nước bằng mấy giọt nước?', 1, 2, 3, 4, 1)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 3, '1 ông tiên có cái bàn không méo và 1 con mèo, bà tiên hàng xóm muốn mua con mèo đó, hỏi ông có bán không?', 'Có', 'Không', 'Tùy hứng', 'Not care :)', 'Không')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 3, 'Cụm từ nào trong môn vật lý, gồm 3 từ, và đặc biệt là 100% giảng viên vật lý không dám viết tắt chúng. 3 từ đó là gì?', 'Cảm ứng từ', 'Suất điện động', 'Hiệu điện thế', 'Định luật ôm', 'Cảm ứng từ')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 3, 'When buy - Black, when use - Red, when throw away - Grey ', 'Coal', 'Electric', 'Glass', 'Money', 'Coal')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 3, 'Cứ 4 vỏ chai nước ngọt thì đổi được 1 chai nước ngọt, hỏi với 16 vỏ chai thì có thể đổi được bao nhiêu chai nước ngọt', '3', '4', '5', '6', '5')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 3, 'Các nguyên tố thuộc nhóm khí hiếm (trừ Heli) có lớp ngoài cùng gồm bao nhiêu electron', '5', '6', '7', '8', '8')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 3, 'Yếu tố chính tạo nên sóng biền là?', 'Nhiệt độ', 'Độ sâu', 'Gió', 'Con người', 'Gió')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 3, 'King of Pop', 'Justin Babie', 'M. Jackson', 'C.Puth', 'Maradona', 'M. Jackson')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Có cách nào xếp 5 que diêm thành 11 hình tam giác không?', 'Được chứ', 'Không thể nào', 'Bẻ que diêm ra', '2 hình là cùng', 'Được chứ')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Con người sẽ chết nếu ăn phải gan của loài động vật nào sau đây?', 'Rắn hổ mang', 'Cá nóc', 'Gấu bắc cực', 'Con người :o', 'Gấu bắc cực')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Bạch tuộc có tất cả bao nhiêu quả tim?', 1, 2, 3, 4, 3)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Sản phẩm nào của động vật có hạn sử dụng vĩnh viễn?', 'Vỏ ốc', 'Tơ nhện', 'Mật ong', 'Méo biết đâu :(', 'Mật ong')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Cơ quan khứu giác của loài ong mật nằm ở...', 'Đầu', 'Bụng', 'Râu', 'Chân', 'Chân')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Loài vật nào sau đây đạt được khoái cảm khi giao phối?', 'Ong', 'Ếch', 'Bạch tuộc', 'Cá heo', 'Cá heo')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Có 1 khúc vải, muốn cắt ra thành 100 khúc, mỗi lần cắt mất 5s. Hỏi sau bao lâu thì cắt xong?', '495s', '490s', '500s', '505s', '495s')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Hai người đào trong hai giờ thì được một cái hố. Vậy hỏi một người đào trong một giờ thì được mấy cái hố?', 1, 2, 3, 4, 1)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Làm sao để cái cân tự cân chính nó?', 'Mua cân khác', 'Dùng cân khác', 'Lật ngược lại', 'Cân đếch được', 'Lật ngược lại')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Anh trai của cháu gái gọi bạn bằng cô là gì của bạn?', 'Anh', 'Cháu', 'Bạn', 'Dì', 'Cháu')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Loài cá nước ngọt nào là động vật ăn thịt?', 'Cá Piranha', 'Cá Chép', 'Cá Nóc', 'Cá Lóc', 'Cá Piranha')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Văn miếu Quốc Tử Giám được xây dựng vào triều nào?', 'Lê', 'Trần', 'Hồ', 'Lý', 'Lý')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Âm thanh có tần số lớn hơn 20KHz gọi là gì?', 'Sóng âm', 'Siêu âm', 'Đặc âm', 'Tử âm', 'Siêu âm')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, '"+"Luận ngữ"+" là tổng hợp các lời dạy của ai', 'Khổng Tử', 'Lê-nin', 'Tôn Tử', 'Tôn Vũ', 'Khổng Tử')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Phân biệt dầu mở bôi trờn và dầu mỡ động vật?', 'Axit', 'Bazơ', 'Quỳ tím', 'Ngu hóa :((', 'Bazơ')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 4, 'Hệ điều hành đầu tiên mà IBM đưa ra cho máy tính cá nhân?', 'Window', 'Linux', 'MS-Dos', 'Mac book', 'MS-Dos')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, 'Trung bình, một người đàn ông có bao nhiêu sinh nhật?', 1, 2, 3, 4, 1)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, 'Chia 30 với 1/2 rồi cộng thêm 10, đáp án là bao nhiêu?', 50, 55, 65, 70, 70)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, 'Bác sĩ đưa cho bạn 3 viên thuốc và bảo bạn cứ 30 phút thì uống một viên. Vậy, chúng tồn tại trong bao nhiêu phút?', 60, 'Hãi não quá...', 70, 80, 60)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, 'Lấy 1.000, cộng thêm 40, lại cộng thêm 1.000, cộng thêm 30, cộng tiếp 1.000, cộng 20, tiếp tục cộng 1.000, cộng 10. Kết quả là bao nhiêu?', 4100, 5100, 6100, 'Bấm casio đê :)', 4100)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, '4-4:(4+4x4:4):4x8=?', '-12', '-6', '6', '12', '-12')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, 'Ở Việt Nam, một thằng mù và ba thằng điếc đi ăn phở, mỗi người ăn một tô. Mỗi tô phở là 10 ngàn đồng, hỏi ăn xong họ phải trả bao nhiêu tiền?', '10k', '20k', '30k', 'Free', '20k')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, 'Bộ phận nào trên người cá voi xanh nặng xấp xỉ một con voi châu Á?', 'Mắt', 'Não', 'Lưỡi', 'Vây', 'Lưỡi')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, 'Con gấu trúc ao ước gì mà không bao giờ được?', 'Chụp hình màu', 'Ăn kẹo', 'Nhậu bia', 'Hỏi gấu ấy!', 'Chụp hình màu')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, 'Ở Việt Nam có 5% số người sử dụng điện thoại không có tên trong danh bạ điện thoại. Nếu ta lấy ngẫu nhiên 100 người trong danh bạ thì trung bình sẽ có bao nhiêu người không có số điện thoại.', '0%', '10%', '20%', '30%', '0%')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, 'Những loài thú nào sau đây ăn cơm:', 'Sư tử', 'Cọp', 'Hà mã', 'Voi', 'Sư tử')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, 'Cơ quan nội tạng dài nhất của con người là:', 'Ruột non', 'Ruột già', 'Xương sống', 'Dạ dày', 'Ruột non')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, 'Răng của con người mọc từ lúc nào', '6 tháng trước sinh', '3 tháng trước sinh', '3 tháng sau sinh', '6 tháng sau sinh', '6 tháng trước sinh')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, 'Vườn quốc gia đầu tiên của Việt Nam', 'Ba Vì', 'Cát Tiên', 'Pù Mát', 'Cúc Phương', 'Cúc Phương')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, '3-8-4=44, 9-7-2=32, 8-7-x=75. x=?(Lưu ý dấu "+"-"+" không phải dấu trừ nhé!)', 5, 8, 15, 13, 5)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, 'Thành phần hóa học chủ yếu của tro bếp là gì?', 'N', 'K', 'O', 'C', 'K')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, '2, 3, 5, 7, ?, ?', '9-11', '9-13', '11-13', '11-15', '11-13')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 5, 'Hiện tượng nào chứng tỏ ánh sáng có tính hạt', 'Phân tán', 'Khúc xạ', 'Phản xạ', 'Quang điện', 'Quang điện')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'Sau khi đầu lìa khỏi cổ thì bộ não vẫn duy trì ý thức. Điều này?', 'Đúng', 'Sai', 'Méo biết', 'Tùy người', 'Đúng')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'Hơn 90% căn bệnh khởi nguồn hoặc trở nên trầm trọng hơn bởi...', 'Vi rút', 'Dinh dưỡng', 'Stress', 'Hỏi thằng Admin', 'Stress')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'Cơ khỏe nhất trong cơ thể chúng ta là:', 'Cơ lưỡi', 'Cơ lưng', 'Cơ tay', 'Cơ bụng', 'Cơ lưỡi')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'Xương ở đâu là cứng nhất?', 'Xương hàm', 'Xương cổ chân', 'Xương sống', 'Ngu sinh học :(', 'Xương hàm')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'Nhóm máu phổ biến nhất thế giới là?', 'A', 'B', 'AB', 'O', 'O')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'Theo các nhà khoa học, trung bình loài người chúng ta thả bom bao nhiêu lần/1 ngày?', 1, 7, 14, 21, 14)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'Hiện tượng vật lí nào làm cho bầu trời có màu xanh là gì?', 'Bức xạ', 'Ánh xạ', 'Phân tán', 'Khúc xạ', 'Phân tán')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'Trái đất hiện nay khoảng bao nhiêu tuổi', '4.54 tỷ tuổi', '5.54 tỷ tuổi', '6.54 tỷ tuổi', 'Ôi mẹ ơi..!!', '4.54 tỷ tuổi')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'Hiện tượng vật lí nào làm xuất hiện cầu vồng', 'Bức xạ', 'Ánh xạ', 'Phân tán', 'Khúc xạ', 'Khúc xạ')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'Sò biển có khoảng bao nhiêu con mắt?', 50, 100, 150, 200, 100)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'Rãnh đại dương Mariana sâu bao nhiêu?', 'Hơn 9km', 'Gần 10km', 'Hơn 11km', 'Gần 12km', 'Hơn 11km')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'Đi sâu vào lòng đất thi trọng lượng của vật như thế nào?', 'Tăng', 'Giảm', 'Không đổi', 'Tăng or Giảm', 'Giảm')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'What is queen of sports', 'Football', 'Voleyball', 'Athlete', 'Tenis', 'Athlete')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'Người ta dùng axit gì để khắc lên thủy tinh', 'FSO3H', 'SbF5', 'HF', 'CF3SO3H', 'HF')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, 'Đại Ngu được thành lập năm nào?', '1340', '1440', '1540', '1640', '1440')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 6, '"+"Nhất Trí, nhì Vân, tam Lân, tứ Cẩn"+" là chỉ bộ tứ đại thụ ở bộ môn nghệ thuật nào?', 'Hội họa', 'Chèo', 'Tuồng', 'Rối nước', 'Hội họa')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'Hệ số thập phân được phát minh bởi nước nào?', 'Hy Lạp', 'Ấn Độ', 'Trung Quốc', 'Ai Cập', 'Ấn Độ')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'Đơn vị khoa học nào được đặt theo tên của một nhà quý tộc người Italy?', 'Pascal', 'Volt', 'Ohm', 'Hertz', 'Volt')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'Vị vua nào lấy Eleanor xứ Aquitaine?', 'Henry I', 'Henry II', 'Richard I', 'Henry V', 'Henry II')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'What are the different between MALE and FEMALE?', 'HAIR', 'SEX', 'FE', 'FAME', 'FE')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'Việt Nam bước vào thời kỳ đổi mới từ năm nào?', 1896, 1986, 1968, 1869, 1968)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'Cacao được trồng nhiều nhất ở đâu?', 'Mexico', 'Hà Lan', 'Brazil', 'Nam Phi', 'Brazil')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'A book with spaces for each day of the year in which you can write down things you hace done. What do you call this?', 'Note book', 'Diary', 'Memoir', 'Board', 'Diary')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'Phố Hàng VẢI - Hà Nội kinh doanh mặt hàng gì là chủ yếu?', 'Vải', 'Tre', 'Gốm', 'Điêu khắc', 'Tre')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'Tỉnh nào sau đây không có biển chạy qua?', 'Hải Dương', 'Thái Bình', 'Nam Định', 'Quảng Ninh', 'Hải Dương')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'Một người đứng trên mũi thuyền cách mặt nước 1,5m. 1 lúc sau nước dâng lên 50cm. Hỏi người đó cách mặt nước bao nhiêu cm?', '100cm', '150cm', '200cm', '250cm', '150cm')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'Biết rằng tổng số tiền mua vợt và bóng cộng lại là 110.000 đồng. Số tiền để mua vợt lớn hơn bóng là 100.000 đồng. Hỏi số tiền cần để mua bóng?', '5.000đ', '8.000đ', '10.000đ', '12.000đ', '5.000đ')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'Cần năm chiếc máy trong năm phút để làm ra năm sản phẩm. Hỏi thời gian cần thiết để 100 chiếc máy tạo ra 100 sản phẩm?', '5p', '10p', '15p', '20p', '5p')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'Trong một cái ao có trồng hoa sen. Mỗi ngày, số lượng hoa sen tăng lên gấp đôi. Biết rằng cần 48 ngày để hoa sen phủ kín ao, hỏi số ngày cần để hoa sen phủ kín chỉ nửa ao', '43 ngày', '45 ngày', '47 ngày', '51 ngày', '47 ngày')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'Năm 1010, Lý Thái Tổ đã cho xây dựng điện nào ở trung tâm hoàng thành Thăng Long', 'Kính Thiên', 'Càn Nguyên', 'Càn Chánh', 'Càn Thành', 'Càn Nguyên')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 7, 'Điêu Tàn là tập thơ đầu tiên của nhà thơ nào?', 'Anh Thơ', 'Thế Lữ', 'Bích Khê', 'Chế Lan Viên', 'Thế Lữ')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Đường thẳng đồng qui chia mặt phẳng ra làm mấy phần?', 3, 5, 7, 9, 7)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Độ cong của thủy tinh thể có thể co giãn được là nhờ sự co giãn của cơ nào ở mắt?', 'Vòng', 'Cau mày', 'Chẩm', 'Trán', 'Vòng')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Trong chiến dịch Điện Biên Phủ, người anh Hùng nào đã lấy thân mình cứu pháo?', 'Tô Vĩnh Diện', 'Phan Đình Giót', 'Bế Văn Đàn', 'Trần Can', 'Tô Vĩnh Diện')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Có bao nhiêu số chẵn có 2 chữ số khác nhau?', 51, 45, 54, 41, 41)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Hiện tượng biến đổi 1 hoặc vài cặp nhiễm sắc thể là loại đột biến gì', 'Đa bội', 'Dịch khung ', 'Đa dị bội', 'Dị bội', 'Dị bội')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Sắc thể hiện hóa trị mấy khi tham gia phản ứng với Acid Nitric đặc?', 2, 3, '2 or 3', 'Không có phản ứng', 3)");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Vĩ tuyến 17 chạy ngang qua tỉnh nào nước ta?', 'Quảng Bình', 'Quảng Ninh', 'Quảng Nam', 'Quảng Trị', 'Quảng Trị')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Trên các bản đồ thế giới cổ xưa, người ta thường ghi tên vị thần nào?', 'Coeus', 'Hyperion', 'Phoebe', 'Atlas', 'Atlas')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Con muỗi hút máu người và động vật để làm gì?', 'Để sống', 'Để nuôi con', 'Nó thích thì hút thôi!', 'Để nuôi trứng', 'Để nuôi trứng')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Vị cua cuối cùng của triều Lý', 'Lý Thái Tổ', 'Lý Anh Tông', 'Lý Huệ Tông', 'Lý Chiêu Hoàng', 'Lý Chiêu Hoàng')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Trên hệ điều hành Windows 10, phím tắt nào mở nhanh tab THÔNG BÁO?', 'IT ơi! help...', 'Windows + A', 'Windows + N', 'Windows + T', 'Windows + A')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Tiêu chí Tự do - Bình đẳng - Bác ái là tiêu chí trong bản tuyên ngôn nước nào?', 'Anh', 'Pháp', 'Nhật', 'Hoa Kỳ', 'Pháp')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Nước nào ở Đông Nam Á không có địa giới với bất kỳ nước nào?', 'Lào', 'Thái Lan', 'Philippines', 'Timor Leste', 'Philippines')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Bảo tàng Hồ Chí Minh khánh thành vào đúng thời điểm sinh nhật lần thứ bao nhiêu của người?', '70', '80', '90', '100', '100')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Trong các hàm lượng giác cơ bản dưới đây. Hàm nào là hàm số chẵn?', 'y = sinx', 'y = cosx', 'y = tgx', 'y = cotgx', 'y = cosx')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Cho đến nay, nước nào thống trị thế giới về xe hơi', 'Mỹ', 'Nhật', 'Ý', 'Đức', 'Đức')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 8, 'Việt Nam giành quyền đăng cai Á Vận Hội (ASIAD) diễn ra vào năm nào?', '2017', '2018', '2019', '2020', '2019')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'Hành tinh nào không có bầu Khí quyển?', 'Kim Tinh', 'Mộc Tinh', 'Thủy Tinh', 'Sao Diêm Vương', 'Thủy Tinh')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'Huyện Nga Son , Tỉnh thanh Hoá Nổi Tiếng Với Nghề Thủ Công Nào?', 'Dệt Chiếu', 'Dệt Vải', 'Làm Chiếu', 'Làm Nón', 'Dệt Chiếu')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'Tim người bình thường đập bao nhiêu nhịp một ngày?', '100.000', '150.000', '200.000', '250.000', '100.000')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'Thụy Điển  nổi tiếng với thể loại nhac nào?', 'Jaz', 'Pop', 'Dance', 'Cổ Điển', 'Pop')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'Tổng thống john Kenede Bị Ám Sát vào Năm Nào?', '1960', '1963', '1965', '1968', '1963')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'Lò phản ứng hạt nhân của nước ta đầu tiên nằm ở đâu?', 'Tp Hồ Chí Minh', 'Đà Lạt', 'Hà Nội', 'Đà Nẵng', 'Đà Lạt')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'Đại Ngu là quốc hiệu của triều nào?', 'Chúa Nguyễn', 'Hồ', 'Ngô', 'Nhà Tây Sơn', 'Hồ')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'Khi rơi tự do vật bị lêch về phương nào?', 'Đông', 'Tây', 'Nam', 'Bắc', 'Đông')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'Khi hoà tan amoni nitrat vào nước, nhiệt độ dung dịch sẽ thay đổi như thế nào?', 'Tăng', 'Giảm', 'Không đổi', 'Em xin thua', 'Giảm')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'Thuỷ ngân được xem là dung môi của các kim loại vì các kim loại dễ tan vào thuỷ ngân ở nhiệt độ thường để tạo nên hợp kim mà người ta thường gọi là?', 'Hỗn hống', 'Thimerosal', 'Huỳnh quang', 'Telurua', 'Hỗn hống')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'Chỉ số nào đặc trưng cho tính chống kích nổ của nhiên liệu động cơ, chỉ số này càng cao, khả năng chống nổ càng tốt?', 'Octan', 'Ron', 'Mon', 'Như nhau thôi!', 'Octan')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'Từ 1 đến 150 có bao nhiêu chữ số 0?', '15', '16', '19', '20', '16')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'The best of toxic snake. what is name it?', 'Snake Taipan', 'Snake Belcher', 'Rattlesnake', 'Snake Mamba', 'Snake Belcher')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'What is flower have best of toxic?', 'Manchineel', 'Jimsonweed', 'White Snakeroot', 'Ricinus communis', 'Manchineel')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'On the surface of compass. The south be given symbol by letter in alphabet. What letter?', 'S', 'N', 'W', 'E', 'S')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'Cầu Phú Mỹ - cây cầu đệp và hiện đại nhất TP Hồ Chí Minh có hình dáng của chữ cái nào?', 'M', 'T', 'H', 'W', 'H')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 9, 'Khí độc Sarin có chứa thành phần nguyên tố Halogen nào?', 'Flo', 'Clo', 'Brom', 'Iot', 'Flo')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Nồng độ CO2 tăng gấp đôi thì nhiệt độ trái đất sẽ tăng bao nhiêu độ C?', '1-2\u00B0C', '2-3\u00B0C', '3-4\u00B0C', '4-5\u00B0C', '2-3\u00B0C')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Ở hành tinh nào trong Hệ mặt trời bạn có thể ăn mừng hai lần sinh nhật trong cùng một ngày??', 'Kim Tinh', 'Mộc Tinh', 'Hỏa Tinh', 'Thủy Tinh', 'Kim Tinh')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Trước khi ra đi tìm đường cứu nước, bác Hồ đã từng dạy học ở tỉnh nào?', 'Nghệ An', 'Bình Thuận', 'Ninh Bình', 'Hà Tĩnh', 'Bình Thuận')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Trong cơ thể người có bao nhiêu loại khớp xương?', '3', '4', '5', '6', '3')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Sài Gòn được chúa Nguyễn đưa vào bản đồ hành chính Việt Nam vào thời gian nào?', '1698', '1689', '1704', '1862', '1689')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Hiện tượng.......xảy ra theo chu kỳ từ 3 đến 11 năm khi nhiệt độ mặt nước biển ở khu vực Tây Thái Bình Dương trở nên ấm hơn so với bình thường?', 'El Nino', 'Milky Sea', 'Brinicle', 'Dead Sea', 'El Nino')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Ngày và thứ của các ngày trong tháng Tư luôn trùng với ngày và thứ của các ngày trong tháng.....', 'Tháng 7', 'Tháng 8', 'Tháng 12', 'Tháng 2', 'Tháng 7')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Huyết áp sinh ra do lực co của cái gì?', 'Tâm Nhĩ', 'Tâm Thất', 'Cả hai', 'Bộ phận khác', 'Tâm Thất')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'What is rays which Rutherford bombarded nuclear of a nitrogen atom?', 'Alpha', 'Lazer', 'Infrared', 'Ultraviolet', 'Alpha')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Các nhà giả kim thuật thời Tây Âu Trung Cổ đã dùng biểu tượng của hành tinh nào để ký hiệu chì?', 'Sao Thổ', 'Sao Hỏa', 'Sao Diêm Vương', 'Sao Tinh', 'Sao Thổ')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Đồng hồ nước đầu tiên được phát minh tại đâu?', 'Ai Cập', 'Hy Lạp', 'Ấn Độ', 'Mông Cổ', 'Ai Cập')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Môn võ Voninam cấp đai cao nhất có màu gì?', 'Đen', 'Đỏ', 'Vàng', 'Trắng', 'Trắng')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Cơ quan nào có tỷ lệ Glycogen cao nhất so với tỉ trọng của nó?', 'Cơ', 'Não', 'Gan', 'Mô mỡ', 'Gan')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Loại vacxin 5 trong 1 DPT-VGB-Hib không có tác dung phòng chống bệnh nào?', 'Bạch hầu', 'Ho gà', 'Uống ván', 'Lao', 'Lao')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Mặt thoáng của khối chất lỏng ở trọng thái phi trọng lượng hoặc chịu tác dụng cửa những lực cân bằng nhau có dạng hình gì?', 'Hình trụ', 'Hình nón', 'Hình cầu', 'Hình vuông', 'Hình cầu')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 10, 'Trong một tứ giác nội tiếp, tích 2 đường chéo bằng tổng các tích của các cặp cạnh đối diện?', 'Carnot', 'Thalet', 'Pompeiu', 'Ptoleme', 'Ptoleme')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'LIVE with EVIL like 5232 with?', '2325', '2523', '3225', '5223', '2325')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'Nhà Thanh có ao bèo 1.000m2, ngày hôm sau số lượng bèo sẽ nở gấp đôi ngày hôm trước, đến ngày thứ 18 thì bèo đã nở được nửa ao. Vậy đến ngày thứ bao nhiêu thì bèo sẽ nở đầy ao?', 'Ngày thứ 1', 'Ngày thứ 19', 'Ngày thứ 36', 'Ngày thứ 42', 'Ngày thứ 19')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'Tiền thuê 1 chỗ đậu xe trong gara là 10 đôla/tuần hoặc 30 đôla/tháng. Một người có thể tiết kiệm được bao nhiêu tiền trong 1 năm nếu thuê theo tháng?', '140', '160', '240', '260', '160')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'John có 10 đôi tất. Nếu anh ta mất 7 chiếc tất riêng lẻ thì số đôi nhiều nhất mà anh ta còn lại là bao nhiêu?', '6', '7', '3', '5', '6')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'Nếu x và y là các số nguyên tố thì các giá trị nào trong các giá trị sau không thể là tổng của x và y?', '100.000', '150.000', '200.000', '250.000', '100.000')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, '2325', 'Hội hoạ', 'Toán học', 'Vật Lý', 'Thiên văn học', 'Thiên văn học')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'Một tờ báo giảm giá 20%. Hỏi nó phải tăng bao nhiêu % để lại có giá như cũ?', '15%', '20%', '25%', '30%', '25%')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'Who is the “Queen of Pop”?', 'Madonna', 'Taylor Swift', 'Katy Perry', 'Miley Cyrus', 'Madonna')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'Từ 1 đến 150 có bao nhiêu chữ số 0?', '14', '15', '16', '17', '16')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'Coren là người đầu tiên phát hiện ra di truyền qua tế bào chất.Theo bạn ông phát hiện ra vào năm nào?', '1899', '1908', '1909', '1890', '1909')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'Which is the mascot of AIG 3', 'Duck', 'Buffalo', 'Snake', 'Chicken', 'Chicken')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'Cứ 4 vỏ chai nước ngọt thì đổi được 01 chai mới. Hỏi với 16 vỏ chai nước ngọt có thể đổi được bao nhiêu chai?', '3', '4', '5', '6', '5')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'Finger is to Hand as Leaf is to:', 'Twig', 'Tree', 'Branch', 'Blossom', 'Tree')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'If you rearrange the letters "+"CIFAIPC"+" you would have the name of a(n):', 'City', 'Animal', 'Ocean', 'River', 'Ocean')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'John needs 13 bottles of water from the store. John can only carry 3 at a time. What is the minimum number of trips John needs to make to the store?', '3', '4', '5', '6', '5')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'Eo biển nào sau đây là nơi phân cách giữa châu Âu và châu Phi', 'Bosphorus', 'Bering', 'Gibralta', 'Malacca', 'Gibralta')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 11, 'Hổ quyền dưới thời nhà Nguyễn là một đấu trường của những cuộc tử chiến giữa hổ và loại động vật nào?', 'Bò tót', 'Báo', 'Sư tử', 'Voi', 'Voi')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'Which of these is not a word or phrase?', 'a lot', 'alot', 'allot', 'lot of', 'a lot')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'CLB nào vô địch FIFA Club World Cup 2010 tổ chức tại Qatar?', 'Inter Milan', 'Real Madrid', 'Bayern Munich', 'Manchester United', 'Inter Milan')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'Thung lũng nổi tiếng ở Mỹ với ngành công nghệ thông tin được đặt tên theo nguyên tố nào?', 'Sắt', 'Asen', 'Uran', 'Silic', 'Silic')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'Which word means feeling great appreciation?', 'grateful', 'greatful', 'gratefull', 'greatfull', 'grateful')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'Fill in the blank: I consulted an attorney _____ I met in New York', 'who', 'whom', 'which', 'that', 'whom')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'Which phrase is erroneous?', 'should have', 'should had', 'should', 'should of', 'should of')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'Vận tốc của sóng âm truyền trong môi trường nào lớn nhất?', 'Chân không', 'Chất rắn', ' Chất lỏng', 'Chất khí', 'Chất khí')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'Thành phố nào của Nhật Bản bị tàn phá nặng nề nhất sau thảm hoạ động đất và sóng thần ngày 11/3/2011?', 'Chiba', 'Kobe', 'Hiroshima', 'Sendai', 'Sendai')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'Bộ phim "+ "W" + " của đạo diễn Oliver Stone viết về ai?', 'Winston Churchil', 'William Shakespear', 'Walt Disney', 'Geroge Walker Bush', 'Geroge Walker Bush')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'Fill in the blank: Bad weather can _____ people is ability to work', 'affect', 'efect', 'afectly', 'effect', 'affect')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'Đơn phân của protein là :', 'Glucose', 'Axit béo', 'Nuclêotit', 'Axit amin', 'Axit amin')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'What is the past tense of the word lie as in lie down?', 'lay', 'laid', 'alay', 'layed', 'lay')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'This sequence of four words, triangle, glove, clock, bicycle, corresponds to this sequence of numbers 3, ?, 12, 2. Choose the correct answer in the following:', '4', '5', '6', '7', '5')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'If written backwards, the number, one thousand, one hundred twenty-five, would be written:', '1150', '1125', '5215', '5211', '5211')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'The word, mineral, can be spelled using only the letters found in the word below: ', 'minefield', 'neptunium', 'parliament', 'militarise', 'parliament')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'Với 1 tam giác bất kỳ, đường tròn chứa chân của 3 đường cao, ba trung điểm của 3 cạnh, 3 trung điểm của 3 đoạn thẳng nối 3 đỉnh với trực tâm gọi là đường tròn gì?', 'Cauchi', 'Viete', 'Newton', 'Euler', 'Euler')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 12, 'Ngày dài nhất và đêm ngắn nhất ở bán cầu Bắc là ngày', 'Xuân phân', 'Thu Phân', 'Đông chí', 'Hạ chí', 'Hạ chí')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'Vào đêm giao thừa ở Nhật Bản thì các chùa đều đánh bao nhiêu tiếng chuông?', '9', '12', '36', '108', '108')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'Nguyên tố hoá học nào có độ âm điện bé nhất và được sử dụng làm tế bào quang điện?', 'Cesi', 'Liti', 'Natri', 'Kali', 'Cesi')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'If someone says I am sorry, you can ... their apology.', 'except', 'accept', 'access', 'acceptance', 'accept')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'Fill in the blank. _____ cold outside!!', 'grateful', 'greatful', 'gratefull', 'greatfull', 'grateful')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'Who is the person in charge at a high school?', 'princile', 'principal', 'princepal', 'princeple', 'principal')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'Hành tinh nào trong Hệ Mặt Trời có chu kỳ tự quay quanh trục và độ nghiêng gần giống Trái Đất nhất?', 'Sao Thuỷ', 'Sao Hoả', 'Sao Mộc', 'Sao Kim', 'Sao Hoả')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'Châu lục nào tiếp giáp với cả Đại Tây Dương và Ấn Độ Dương?', 'Châu Á', 'Châu Âu', 'Châu Phi', 'Châu Mĩ', 'Châu Phi')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'Loại kháng thể duy nhất có thể truyền qua rau thai?', 'IgA', 'IgM', 'IgG', 'IgD', 'IgG')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'Quốc hội Việt Nam gia nhập AIPO (nay là AIPA) vào năm nào?', '1995', '2000', '2005', '2010', '1995')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'Ngày 24/3 là ngày thế giới phòng chống ...?', 'Lao', 'Phong', 'Ung thư', 'Cổ chướng', 'Lao')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'Which word means: unaware or ignorant?', 'obvious', 'oblivios', 'obliver', 'obver', 'oblivios')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'When your team doesn it win the game, they most definitely..', 'lose', 'loose', 'grateful', 'greatfull', 'grateful')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'When was the Titanic wrecked?', '1910', '1911', '1912', '1913', '1912')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'Sâm Thương nghĩa là gì', 'Cách biệt', 'Chung thủy', 'Sai lầm', 'Đoàn tụ', 'Cách biệt')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'Gary has amount of money. He can buy a bicycle that costs one hundred twenty dollars if he borrows fifty-seven dollars from Jane and fifteen dollars from Jill. How much money does Gary has?', 'Forty-eight', 'Fifty-two', 'Fifty-five', 'Fifty-eight', 'Forty-eight')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'Ralph likes 25 but not 24; he likes 400 but not 300; he likes 144 but not 145. Which does he like:', '50', '124', '200', '1600', '1600')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 13, 'Which one of the numbers does not belong in the following series: 2 - 3 - 6 - 7 - 8 - 14 - 15 - 30', 'THREE', 'SEVEN', 'EIGHT', 'FIFTEEN', 'EIGHT')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 14, 'Titan là vệ tinh duy nhất trong hệ mặt trời có khí quyển và cũng là vệ tinh lớn nhất của hành tinh nào?', 'Sao Thuỷ', 'Sao Thổ', 'Sao Mộc', 'Sao Kim', 'Sao Thổ')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 14, 'When you stay in a country for some time you get used to the people is ...... of life.', 'habit', 'custom', 'way', 'system', 'custom')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 14, 'Cơ quan nào sau đây dự trữ tỉ lệ glycogen cao nhất so với tỉ trọng của nó?', 'Cơ', 'Não', 'Mô mỡ', 'Gan', 'Gan')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 14, 'What is chemical element caled element of life and thinking?', 'N', 'P', 'O', 'S', 'P')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 14, 'What is continent complete located at the western hemisphere?', 'Europe', 'Americas', 'Africa', 'Asia', 'Americas')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 14, 'Disease to do whatever one like in century XIV, it is called Black Death. What is disease?', 'Smallpox', 'Bubonic plague', 'Mad cow', 'flu', 'Bubonic plague')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 14, 'Hòn đảo là nơi duy nhất mà chủ tịch Hồ Chí Minh đồng ý cho dựng tượng khi người còn sống?', 'Cát Bà', 'Phú Quốc', 'Cô Tô', 'Cồn Cỏ', 'Cô Tô')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 14, 'How many Acid type?', '3', '4', '5', '6', '4')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 14, 'Số La Mã nào sau đây có giá trị lớn nhất?', 'X', 'C', 'L', 'M', 'M')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 14, 'Have a moutain higher Everest, it is named Maxwell Montes. Where is it?', 'Mercury', 'Mars', 'Venus', 'Jupiter', 'Venus')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 14, 'What is the consumer price index symbol?', 'HDI', 'CPI', 'GEI', 'FDI', 'CPI')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 15, 'Which country is homeland of Age of Enlightenment and Mighty empire with the Bourbons in XVIII century?', 'England', 'France', 'Germany', 'Italia', 'France')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 15, '9 – 6 - 1, 27 – 1 - 2, 6 - 3 - ?', '2', '3', '4', '5', '3')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 15, '2 - 8 - 20 - 40 - 70 - ?', '100', '112', '120', '124', '112')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 15, '4 - 28 - 70 - 130 - 208 - ?', '286', '304', '340', '388', '304')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 15, 'A number one followed by one hundred zeros is know by what name?', 'Googol', 'Megatron', 'Gigabit', 'Nanomole', 'Googol')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 15, 'Which of these U.S Presidents appeared on the television series "+" Laugh-In"+"?', 'Lyndon Johnson', 'Richard Nixon', 'Jimmy Carter', 'Gerald Ford', 'Richard Nixon')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 15, 'Baron Haussmann is best known for hos planing of which city?', 'Rome', 'Paris', 'Berlin', 'Athens', 'Paris')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 15, '3829718 is to 87283 and 642735 is to 5346 therefore 6917 is to ?', '67', '76', '69', '91', '76')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 15, 'What numbers should replace the question marks? 100, 95, ?, 79, 68, ?, 40, 23', '70 and 55', '80 and 55', '88 and 50', '88 and 55', '88 and 55')");
        db.QueryData("INSERT INTO Question_Table VALUES(null, 15, 'Only one group of five letters below can be rearrangedto spell out a five-letter word in the English language. indentify the word?', 'LEVUR', 'TNIEC', 'HEOLC', 'ANOIP', 'ANOIP')");
    }
}
