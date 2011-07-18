use FreeUWING::FUWUtils::FUWParser;

## インスタンスを生成する
$fpwword2	= FreeUWING::FUWUtils::Word2->new();
$fpwheading	= FreeUWING::FUWUtils::Heading->new();
$fpwtext	= FreeUWING::FUWUtils::Text->new();

# 書き込み用の作業ファイルを開く
$fpwword2->open() 	|| die $fpwword2->error_message(). "\n";
$fpwheading->open() 	|| die $fpwheading->error_message(). "\n";
$fpwtext->open() 	|| die $fpwtext->error_message(). "\n";

my $input1 = $ARGV[0];
my $input2 = $ARGV[1];
print $input1. " and ". $input2;
