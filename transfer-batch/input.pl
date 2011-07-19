use FreeUWING::FUWUtils::FUWParser;

## インスタンスを生成する
$fpwword2	= FreeUWING::FUWUtils::Word2->new();
$fpwheading	= FreeUWING::FUWUtils::Heading->new();
$fpwtext	= FreeUWING::FUWUtils::Text->new();

# 書き込み用の作業ファイルを開く
$fpwword2->open() 	|| die $fpwword2->error_message(). "\n";
$fpwheading->open() 	|| die $fpwheading->error_message(). "\n";
$fpwtext->open() 	|| die $fpwtext->error_message(). "\n";

for(;;){
  last if (!defined($_ = <>));
  chomp;

  $fpwtext->new_entry()
    || die $fpwtext->error_message(). "\n";
  $fpwheading->new_entry()
    || die $fpwheading->error_message(). "\n";
  #見だし
  $fpwheading->add_text($_)
    || die $fpwheading->error_message(). "\n";

  #本文
  if (!$fpwtext->add_keyword_start()
    || !$fpwtext->add_text($_)
    || !$fpwtext->add_keyword_end()
    || !$fpwtext->add_newline()){
    die $fpwtext->error_message(). "\n";
  }

  #かなの検索語を登録する
  ($kanaword = $_) =~ s/【.*$//;
  $heading_position = $fpwheading->entry_position();
  $text_position = $fpwtext->entry_position();
  if( !$fpwword2->add_entry($kanaword, $heading_position, 'head',
    $text_position, 'text')){
    die $fpwword2->error_message(). "\n";
  }

  # 次の一行　本文　を読み込む
  last if (!defined($_ = <>));

  # 本文を書き込む
  if(!$fpwtext->add_text($_)
    || !$fpwtext->add_newline()){
    die $fpwtext->error_message(). "\n";
  }
}


## 書き込み用の作業ファイルを閉じる
$fpwword2->close()    || die $fpwword2->error_message(). "\n";
$fpwheading->close()  || die $fpwheading->error_message(). "\n";
$fpwtext->close()     || die $fpwtext->error_message(). "\n";

my $input1 = $ARGV[0];
my $input2 = $ARGV[1];
print $input1. " and ". $input2;
