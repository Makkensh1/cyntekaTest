
public class ResultCompiler {

    public static void getResult() {
        Context context = new Context();
        context.setFirstLine(InputFileReader.getFirstSelection());
        context.setSecondLine(InputFileReader.getSecondSelection());
        WordsMapper.compareLines(context);
        context.setSecondLineAsWords(LineFormatter.getLineByWords(context.getSecondLine()));
        context.setFirstLineAsWords(LineFormatter.getLineByWords(context.getFirstLine()));
        WordsMapper.compareWordsInLines(context);
        context.setFirstLineAsWords(LineFormatter.getLineByWordsInFirstForm(context.getFirstLineAsWords()));
        context.setSecondLineAsWords(LineFormatter.getLineByWordsInFirstForm(context.getSecondLineAsWords()));
        WordsMapper.compareWordsInLines(context);
        WordsMapper.compareLinesByMeaning(context);
        WordsMapper.collectNotFoundPairs(context);
        OutPutFileWriter.writeFile(context.getResult());
    }
}
