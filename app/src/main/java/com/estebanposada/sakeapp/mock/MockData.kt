package com.estebanposada.sakeapp.mock

import com.estebanposada.sakeapp.domain.model.Sake as DomainSake

data class Sake(
    val name: String,
    val description: String,
    val picture: String?,
    val rating: Double,
    val address: String,
    val coordinates: List<Double>,
    val mapLink: String,
    val website: String,
)

val mockSakeList = listOf(
    Sake(
        name = "信州スシサカバ 寿しなの",
        description = "Sushi bar with a variety of sake options.",
        picture = "http://ts1.mm.bing.net/th?id=OIP.GURnZicaENMLYBMZN9k1LwHaFS&pid=15.1",
        rating = 4.0,
        address = "〒380-0824 長野県長野市南長野南石堂町1421",
        coordinates = listOf(36.644257, 138.18668),
        mapLink = "https://maps.app.goo.gl/4fYMDSfNd6ocsDwt6",
        website = "https://www.sushinano.com/"
    ),
    Sake(
        name = "長野県酒類販売(株)",
        description = "Wholesale sake distributor.",
        picture = "https://www.nagano-sake.com/common/images/front/drinks-xxl@2x.jpg",
        rating = 4.2,
        address = "〒380-0835 長野県長野市新田町1464",
        coordinates = listOf(36.629883, 138.21141),
        mapLink = "https://maps.app.goo.gl/wRD6LRQc7Ct9QXMG8",
        website = "http://www.nagano-sake.com/"
    ),
    Sake(
        name = "Kadode",
        description = "Japanese restaurant with high-quality sake.",
        picture = "https://rimage.hitosara.com/gg/image/0004030762/0004030762I1_1024x767y.webp",
        rating = 4.4,
        address = "〒380-0824 長野県長野市南長野南石堂町1421",
        coordinates = listOf(36.648273, 138.18724),
        mapLink = "https://maps.app.goo.gl/4z71FzXNYYM5oT2GA",
        website = "https://hitosara.com/0004030762/"
    ),
    Sake(
        name = "Midori Nagano",
        description = "Shopping center with a variety of sake shops.",
        picture = null,
        rating = 4.0,
        address = "〒380-0824 長野県長野市南長野南石堂町1421",
        coordinates = listOf(36.64396, 138.18893),
        mapLink = "https://maps.app.goo.gl/yPqYieSzdpkyCSd98",
        website = "https://www.eki-midori.com/nagano/"
    ),
    Sake(
        name = "Endo Brewery",
        description = "Historic brewery known for its Keiryu brand sake.",
        picture = "https://www.keiryu.jp/img_201904/head_parts/shop_img.png",
        rating = 4.5,
        address = "〒382-0086 長野県須坂市大字須坂29",
        coordinates = listOf(36.648273, 138.18724),
        mapLink = "https://maps.app.goo.gl/f288RPXsgHRch3297",
        website = "https://www.keiryu.jp/"
    ),
    Sake(
        name = "Masuichi Brewery",
        description = "Brewery located in Obuse, known for its traditional sake.",
        picture = "https://gigaplus.makeshop.jp/masuichi/assets_v2/img/common/slide1.jpg",
        rating = 4.3,
        address = "〒381-0201 長野県上高井郡小布施町大字小布施807",
        coordinates = listOf(36.648273, 138.18724),
        mapLink = "https://maps.app.goo.gl/qrZekaJq8qP3yMiy7",
        website = "https://www.masuichi.com/"
    ),
    Sake(
        name = "Miyasaka Brewery",
        description = "Brewery in Suwa City, offering a variety of sake.",
        picture = "https://www.masumi.co.jp/en/wp/wp-content/uploads/2025/02/top_1.jpg",
        rating = 4.2,
        address = "〒392-0004 長野県諏訪市諏訪2丁目9-25",
        coordinates = listOf(36.648273, 138.18724),
        mapLink = "https://maps.app.goo.gl/wJTiquoJ4Qj8xsUp6",
        website = "http://www.masumi.co.jp/"
    ),
    Sake(
        name = "Tamamura Honten",
        description = "Brewery in Yamanouchi, known for its sake and beer.",
        picture = "https://img07.shop-pro.jp/PA01336/957/slideshow/slideshow_img1.jpg?p_timestamp=20160118182621",
        rating = 4.6,
        address = "〒381-0401 長野県下高井郡山ノ内町大字平穏1163",
        coordinates = listOf(36.648273, 138.18724),
        mapLink = "https://maps.app.goo.gl/htbv7LKDc9N3aHZ9A",
        website = "http://www.tamamura-honten.co.jp/"
    ),
    Sake(
        name = "Shinshu Kuraudo",
        description = "Specialty shop offering sake from around Nagano.",
        picture = "https://www.nagano-wine.jp/store/.assets/_DSC6668.jpg",
        rating = 4.1,
        address = "〒380-0824 長野県長野市南長野南石堂町1421",
        coordinates = listOf(36.648273, 138.18724),
        mapLink = "https://maps.app.goo.gl/SuUjW4R3wzuj3YVWA",
        website = "https://www.nagano-wine.jp/store/pickup/post-34/"
    ),
    Sake(
        name = "Hokuan Brewery",
        description = "Small brewery in Omachi City, known for its Daikokumasamune sake.",
        picture = "https://hokuan.co.jp/wp/wp-content/themes/hokuan/img/bg01.jpg",
        rating = 4.0,
        address = "〒398-0002 長野県大町市大町2340-1",
        coordinates = listOf(36.648273, 138.18724),
        mapLink = "https://maps.app.goo.gl/GQrMNVo6YoVEvCPU6",
        website = "https://hokuan.co.jp/"
    ),
    Sake(
        name = "Hokuan Brewery",
        description = "Small brewery in Omachi City, known for its Daikokumasamune sake.",
        picture = "https://hokuan.co.jp/wp/wp-content/themes/hokuan/img/bg01.jpg",
        rating = 4.0,
        address = "〒398-0002 長野県大町市大町2340-1",
        coordinates = listOf(36.648273, 138.18724),
        mapLink = "https://maps.app.goo.gl/GQrMNVo6YoVEvCPU6",
        website = "https://hokuan.co.jp/"
    )
)

fun Sake.toDomainSake() = DomainSake(
    name = name,
    description = description,
    picture = picture,
    rating = rating.toFloat(),
    address = address,
    coordinates = coordinates,
    google_maps_link = mapLink,
    website = website
)