package org.datastream.stream.impl;

import java.net.URI;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.datastream.stream.DataStream;
import org.datastream.stream.func.DataFilter;
import org.datastream.stream.func.MapFieldFunction;

import cascading.flow.Flow;
import cascading.flow.FlowDef;
import cascading.flow.local.LocalFlowConnector;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.pipe.assembly.Discard;
import cascading.pipe.assembly.Rename;
import cascading.pipe.assembly.Retain;
import cascading.pipe.assembly.Unique;
import cascading.scheme.Scheme;
import cascading.scheme.local.TextDelimited;
import cascading.tap.Tap;
import cascading.tap.local.FileTap;
import cascading.tuple.Fields;

/**
 * The local csv dataStream implementation which is able to provide the solid implementation for the client
 * 
 * 
 * @author kenny.li
 *
 */
public class LocalCSVDataStream implements DataStream<CSVStreamData> {
    private LocalCSVStreamSource source;
    private Pipe sourcePipe;
    private FlowDef flowDef = new FlowDef();

    private LinkedList<Pipe> pipes = new LinkedList<Pipe>();
    private String name;

    LocalCSVDataStream(String name, LocalCSVStreamSource dataSource) {
        assert dataSource != null;
        this.name = name;

        this.source = dataSource;
        sourcePipe = new Pipe(name + ":source");
        pipes.push(sourcePipe);

    }

    @Override
    public Stream<CSVStreamData> filter(Predicate<? super CSVStreamData> predicate) {
        pipes.push(new Each(pipes.getLast(), new DataFilter(predicate)));
        return this;
    }

    @Override
    public <R> Stream<R> map(Function<? super CSVStreamData, ? extends R> mapper) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super CSVStreamData, ? extends Stream<? extends R>> mapper) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public DataStream<CSVStreamData> distinct() {
        Unique unique = new Unique(pipes.getLast(), Fields.ALL);
        pipes.push(unique);
        return this;
    }

    @Override
    public DataStream<CSVStreamData> sorted() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataStream<CSVStreamData> sorted(Comparator<? super CSVStreamData> comparator) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Stream<CSVStreamData> peek(Consumer<? super CSVStreamData> action) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Stream<CSVStreamData> limit(long maxSize) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Stream<CSVStreamData> skip(long n) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void forEach(Consumer<? super CSVStreamData> action) {
        // TODO Auto-generated method stub

    }

    @Override
    public void forEachOrdered(Consumer<? super CSVStreamData> action) {
        // TODO Auto-generated method stub

    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CSVStreamData reduce(CSVStreamData identity, BinaryOperator<CSVStreamData> accumulator) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<CSVStreamData> reduce(BinaryOperator<CSVStreamData> accumulator) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super CSVStreamData, U> accumulator, BinaryOperator<U> combiner) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super CSVStreamData> accumulator,
            BiConsumer<R, R> combiner) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <R, A> R collect(Collector<? super CSVStreamData, A, R> collector) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<CSVStreamData> min(Comparator<? super CSVStreamData> comparator) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<CSVStreamData> max(Comparator<? super CSVStreamData> comparator) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean anyMatch(Predicate<? super CSVStreamData> predicate) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean allMatch(Predicate<? super CSVStreamData> predicate) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean noneMatch(Predicate<? super CSVStreamData> predicate) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<CSVStreamData> findFirst() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<CSVStreamData> findAny() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<CSVStreamData> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Spliterator<CSVStreamData> spliterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isParallel() {
        return false;
    }

    @Override
    public Stream<CSVStreamData> sequential() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Stream<CSVStreamData> parallel() {
        // No effect
        return this;
    }

    @Override
    public Stream<CSVStreamData> unordered() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Stream<CSVStreamData> onClose(Runnable closeHandler) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public DataStream<CSVStreamData> tail(long num) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataStream<CSVStreamData> project(String... fieldNames) {
        Fields fields = new Fields();
        for (String fieldName : fieldNames) {
            fields = fields.append(new Fields(fieldName));
        }
        Retain retain = new Retain(pipes.getLast(), fields);
        pipes.add(retain);
        return this;
    }

    @Override
    public DataStream<CSVStreamData> discard(String... fieldsName) {
        Fields fields = new Fields();
        for (String fieldName : fieldsName) {
            fields = fields.append(new Fields(fieldName));
        }
        Discard discard = new Discard(pipes.getLast(), fields);
        pipes.add(discard);
        return this;
    }

    @Override
    public DataStream<CSVStreamData> addFields(Function<CSVStreamData, String[]> function, String... fields) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataStream<CSVStreamData> rename(String name, String targetName) {
        pipes.add(new Rename(pipes.getLast(), new Fields(name), new Fields(targetName)));
        return this;
    }

    @Override
    public DataStream<CSVStreamData> mapTo(String sourceField, String newField, Function<String, String> function) {

        MapFieldFunction func = new MapFieldFunction(sourceField, newField);
        func.setFunction(function);
        Pipe pipe = new Each(pipes.getLast(), Fields.ALL, func);
        pipes.add(pipe);
        return this;
    }

    @Override
    public DataStream<CSVStreamData> mapField(Function<String, String> function) {
        return null;
    }

    @Override
    public DataStream<CSVStreamData> leftJoin(Stream<CSVStreamData> rightStream) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataStream<CSVStreamData> rightJoin(Stream<CSVStreamData> rightStream) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataStream<CSVStreamData> innerJoin(Stream<CSVStreamData> rightStream) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataStream<CSVStreamData> outerJoin(Stream<CSVStreamData> rightStream) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void writeTo(URI location) {
        Scheme scheme = new TextDelimited(Fields.ALL, ",", "'");
        Tap sinkTap = new FileTap(scheme, location.getPath());
        flowDef = flowDef.addSource(sourcePipe, source.getSourceTap())
                .addTailSink(pipes.getLast(), sinkTap).setName(name);
        assert flowDef != null;
        Flow flow = new LocalFlowConnector().connect(flowDef);
        flow.complete();
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super CSVStreamData> mapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super CSVStreamData> mapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super CSVStreamData> mapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IntStream flatMapToInt(Function<? super CSVStreamData, ? extends IntStream> mapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LongStream flatMapToLong(Function<? super CSVStreamData, ? extends LongStream> mapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super CSVStreamData, ? extends DoubleStream> mapper) {
        throw new UnsupportedOperationException();
    }

}
